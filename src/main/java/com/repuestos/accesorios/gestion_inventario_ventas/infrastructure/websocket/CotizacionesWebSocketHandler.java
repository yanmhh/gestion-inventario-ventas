package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception.MessageProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Component
public class CotizacionesWebSocketHandler extends AbstractWebSocketHandler {
    @Override
    protected void handleMessage(WebSocketSession session, Map<String, Object> payload) {
        var action = (String) payload.get("action");
        if (action == null) throw new MessageProcessingException("Acción no especificada");

        switch (action) {
            case "newCotizacion" -> {
                Object cotizacion = payload.get("cotizacion");
                Map<String, Object> msg = Map.of(
                        "type", "cotizacionCreated",
                        "cotizacion", cotizacion
                );
                broadcast(msg);
                log.info("Notificación cotización creada broadcast");
            }

            case "requestCotizacion" -> {
                // ejemplo: enviar a una session específica
                Object targetSessionId = payload.get("sessionId");
                if (targetSessionId != null) {
                    try {
                        sendToSession(targetSessionId.toString(), Map.of("type", "cotizacionResponse", "data", Map.of()));
                    } catch (Exception e) {
                        throw new MessageProcessingException("No se pudo enviar respuesta a la session objetivo");
                    }
                } else {
                    // fallback: enviar lista vacía
                    try {
                        session.sendMessage(new org.springframework.web.socket.TextMessage(mapper.writeValueAsString(Map.of("type", "cotizacionResponse", "data", Map.of()))));
                    } catch (Exception e) {
                        throw new MessageProcessingException("No se pudo enviar respuesta");
                    }
                }
            }

            default -> throw new MessageProcessingException("Acción desconocida: " + action);
        }
    }
}
