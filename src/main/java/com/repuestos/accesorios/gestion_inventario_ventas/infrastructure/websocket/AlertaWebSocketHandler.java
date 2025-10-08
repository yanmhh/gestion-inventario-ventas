package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception.MessageProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import java.util.Map;


@Component
public class AlertaWebSocketHandler extends  AbstractWebSocketHandler{
    @Override
    protected void handleMessage(WebSocketSession session, Map<String, Object> payload) {
        // Estructura esperada: { "action": "sendAlert", "level":"INFO|WARN|ERROR", "message":"..." }
        var action = (String) payload.get("action");
        if (action == null) throw new MessageProcessingException("Acción no especificada");

        if (action.equals("sendAlert")) {
            String level = (String) payload.getOrDefault("level", "INFO");
            String message = (String) payload.getOrDefault("message", "");
            // construye un objeto de alerta (json)
            Map<String, Object> alert = Map.of(
                    "type", "alert",
                    "level", level,
                    "message", message,
                    "source", "server"
            );
            // Envia a todos
            broadcast(alert);
            log.info("Alerta enviada level={} message={}", level, message);
        } else {
            throw new MessageProcessingException("Acción desconocida: " + action);
        }
    }
}
