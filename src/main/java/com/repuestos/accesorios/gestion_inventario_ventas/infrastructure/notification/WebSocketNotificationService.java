package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.notification;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta.AlertaView;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void enviarAlertaGeneral(AlertaView alerta) {
        messagingTemplate.convertAndSend("/topic/alertas", alerta);
    }

    public void enviarAlertaAUsuario(Integer usuarioId, AlertaView alerta) {
        messagingTemplate.convertAndSend("/queue/alertas-" + usuarioId, alerta);
    }
}
