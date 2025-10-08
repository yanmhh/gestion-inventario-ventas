package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception.MessageProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Component
public class VentasWebSocketHandler extends AbstractWebSocketHandler{

    @Override
    protected void handleMessage(WebSocketSession session, Map<String, Object> payload) {
        var action = (String) payload.get("action");
        if (action == null) throw new MessageProcessingException("Acción no especificada");

        switch (action) {
            case "newSale" -> {
                // payload contiene info de la venta (puedes validar lo mínimo y después procesarlo en application layer)
                Object venta = payload.get("venta");
                Map<String, Object> msg = Map.of(
                        "type", "saleCreated",
                        "venta", venta
                );
                // broadcast a interesados (o usar lógica para enviar a roles específicos)
                broadcast(msg);
                log.info("Notificación venta creada broadcast");
            }

            case "getRecentSales" -> {
                // Podrías enviar una respuesta con las ventas recientes (consulta al repo desde otro servicio)
                // aquí solo demostración de respuesta directa
                try {
                    session.sendMessage(new org.springframework.web.socket.TextMessage(mapper.writeValueAsString(Map.of("type", "recentSales", "sales", java.util.List.of()))));
                } catch (Exception e) {
                    throw new MessageProcessingException("No se pudo enviar recientes ventas");
                }
            }

            default -> throw new MessageProcessingException("Acción desconocida: " + action);
        }
    }

}
