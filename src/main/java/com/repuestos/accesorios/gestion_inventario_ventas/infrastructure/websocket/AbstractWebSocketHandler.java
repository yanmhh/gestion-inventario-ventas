package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception.MessageProcessingException;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception.SessionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class AbstractWebSocketHandler extends TextWebSocketHandler {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    protected final ConcurrentHashMap<String, CopyOnWriteArraySet<String>> userSessions = new ConcurrentHashMap<>();
    protected final ObjectMapper mapper = new ObjectMapper();

    //metodo de gestión de conexión.
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        //se ejecuta cuanda un usuario se conecta
        sessions.add(session);
        registerUserSessionIfPresent(session);
        log.info("WebSocket conectado [{}] uri={} principal={}", session.getId(), session.getUri(), session.getPrincipal());
    }

    //manejo de errores en la conexión
    @Override
    public void handleTransportError(@NonNull WebSocketSession session, @NonNull Throwable exception) {
        log.error("Error transporte en sesión {}: {}", session.getId() , exception.getMessage(), exception);
        closeSilently(session);
    }

    //se ejecuta cuando un usuario se desconecta
    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        sessions.remove(session);
        unregisterUserSessionIfPresent(session);
        log.info("WebSocket desconectado [{}] status={}", session.getId(), status);
    }

    //procesa mensajes entrantes
    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
        try {
            String payloadText = message.getPayload();
            if (payloadText.isBlank()) {
                log.warn("Mensaje vacío recibido en sesión {}", session.getId());
                return;
            }

            Map<String, Object> payload = mapper.readValue(payloadText, new TypeReference<>() {});
            handleMessage(session, payload);

        } catch (MessageProcessingException mpe) {
            log.warn("Error procesando mensaje: {}", mpe.getMessage());
            sendError(session, mpe.getMessage());
        } catch (Exception e) {
            log.error("Error procesando mensaje en session{} " , session.getId(), e);
            sendError(session, "Error interno procesando mensaje");
        }
    }

    //metodo que se implementa en las clases
    protected abstract void handleMessage(WebSocketSession session, Map<String, Object> payload) throws Exception;

    //envía a todas las sesiones abiertas
    public void broadcast(Object object) {

        try {
            String text = mapper.writeValueAsString(object);
            sessions.forEach(s -> {
                if (s.isOpen()) {
                    try {
                        s.sendMessage(new TextMessage(text));
                    } catch (IOException e) {
                        log.warn("Error enviando broadcast a {}: {}", s.getId(), e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            log.error("Error serializando mensaje para broadcast", e);
        }
    }

    // enviar a una sessíon específica por su ID
    public void sendToSession(String sessionId, Object object) {
        Optional<WebSocketSession> opt = sessions.stream().filter(s -> s.getId().equals(sessionId)).findFirst();
        if (opt.isEmpty()) throw new SessionNotFoundException("Sesión no encontrada: " + sessionId);
        try {
            opt.get().sendMessage(new TextMessage(mapper.writeValueAsString(object)));
        } catch (IOException e) {
            throw new MessageProcessingException("No se pudo enviar mensaje a la sesión  " + sessionId);
        }
    }

    // enviar un mensaje a todas las sesiones de un usuario por userId
    public void sendToUser(String userId, Object object) {
        var sessionIds = userSessions.get(userId);
        if (sessionIds == null || sessionIds.isEmpty()) throw new SessionNotFoundException("No hay sesiones activas para el usuario: " + userId);
        sessionIds.forEach(this::safeSendBySessionId);
    }

    //enviar mensaje seguro por sessionId
    private void safeSendBySessionId(String sessionId) {
        sessions.stream().filter(s -> s.getId().equals(sessionId)).findFirst().ifPresent(s -> {
            if (s.isOpen()) {
                try {
                    s.sendMessage(new TextMessage(mapper.writeValueAsString(Map.of("payload", "update"))));
                } catch (IOException e) {
                    log.warn("Error enviando mensaje a session {}: {}", sessionId, e.getMessage());
                }
            }
        });
    }

    //enviar mensaje de error
    protected void sendError(WebSocketSession session, String message) {
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(Map.of(
                    "type", "error",
                    "message", message
            ))));
        } catch (Exception e) {
            log.warn("No se pudo enviar mensaje de error a session {}: {}", session.getId(), e.getMessage());
        }
    }

    //cierra sesión de manera segura y sileciosa
    private void closeSilently(WebSocketSession session) {
        if (session == null) return;
        try {
            if (session.isOpen()) session.close(CloseStatus.SERVER_ERROR);
        } catch (IOException e) {
            log.warn("Error cerrando sesión {}", session.getId(), e);
        }
    }

    // Intenta registrar userId extraído de query params o attributes
    private void registerUserSessionIfPresent(WebSocketSession session) {
        try {
            // Si autenticación coloca usuario en attributes (por ejemplo via interceptor)
            Object uid = session.getAttributes().get("userId");
            if (uid != null) {
                userSessions.computeIfAbsent(uid.toString(), k -> new CopyOnWriteArraySet<>()).add(session.getId());
                return;
            }

            // Extraer userId desde query string si está presente: ?userId=xxx
            if (session.getUri() != null && session.getUri().getQuery() != null) {
                String[] parts = session.getUri().getQuery().split("&");
                for (String part : parts) {
                    if (part.startsWith("userId=")) {
                        String userId = part.substring("userId=".length());
                        userSessions.computeIfAbsent(userId, k -> new CopyOnWriteArraySet<>()).add(session.getId());
                        return;
                    }
                }
            }
        } catch (Exception e) {
            log.debug("No se pudo registrar userId para session {}: {}", session.getId(), e.getMessage());
        }
    }

    //elimina la sesión cuando un usuario se desconecta
    private void unregisterUserSessionIfPresent(WebSocketSession session) {
        try {
            userSessions.forEach((userId, set) -> {
                set.remove(session.getId());
                if (set.isEmpty()) userSessions.remove(userId);
            });
        } catch (Exception e) {
            log.debug("Error limpiando userSessions para session {}: {}", session.getId(), e.getMessage());
        }
    }
}
