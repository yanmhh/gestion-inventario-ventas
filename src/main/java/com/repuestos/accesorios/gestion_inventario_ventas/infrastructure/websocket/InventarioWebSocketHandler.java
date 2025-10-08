package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket;


import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception.MessageProcessingException;
import org.springframework.stereotype.Component;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;


@Component
public class InventarioWebSocketHandler  extends AbstractWebSocketHandler {

    @Override
    protected void handleMessage(WebSocketSession session, Map<String, Object> payload) {
        // Espera actions: updateStock, subscribeProduct
        var action = (String) payload.get("action");
        if (action == null) throw new MessageProcessingException("Acción no especificada");

        switch (action) {
            case "updateStock" -> {
                // payload: { action:"updateStock", productoId: 12, stock: 34 }
                Integer productoId = (Integer) payload.get("productoId");
                Integer stock = (Integer) payload.get("stock");
                if (productoId == null || stock == null) throw new MessageProcessingException("productoId y stock son requeridos");
                Map<String, Object> event = Map.of(
                        "type", "stockUpdated",
                        "productoId", productoId,
                        "stock", stock
                );
                broadcast(event);
                log.info("Stock actualizado broadcast productoId={} stock={}", productoId, stock);
            }

            case "subscribeProduct" -> {
                // Puedes implementar lógica de suscripción por producto (a futuro)
                log.debug("Subscripción recibida: {}", payload);
                // confirma al cliente
                try {
                    session.sendMessage(new org.springframework.web.socket.TextMessage(mapper.writeValueAsString(Map.of("type", "subscribed", "payload", payload))));
                } catch (Exception e) {
                    throw new MessageProcessingException("No se pudo confirmar suscripción");
                }
            }

            default -> throw new MessageProcessingException("Acción desconocida: " + action);
        }
    }
}
