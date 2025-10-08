package com.repuestos.accesorios.gestion_inventario_ventas.application.service.notificacion;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.AlertaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajo;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.config.AlertaProperties;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.email.EmailService;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.AlertaWebSocketHandler;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {
    private final EmailService emailService;
    private final AlertaWebSocketHandler alertaWebSocketHandler;
    private final AlertaService alertaService;
    private final AlertaProperties alertaProperties;

    public NotificacionService(
            EmailService emailService,
            AlertaWebSocketHandler alertaWebSocketHandler,
            AlertaService alertaService,
            AlertaProperties alertaProperties
    ) {
        this.emailService = emailService;
        this.alertaWebSocketHandler = alertaWebSocketHandler;
        this.alertaService = alertaService;
        this.alertaProperties = alertaProperties;
    }

    public void enviarNotificaciones(StockBajo event) {
        String mensaje = String.format(
                "⚠️ Stock bajo: El producto '%s' tiene %d unidades (mínimo permitido: %d)",
                event.getNombreProducto(),
                event.getStockActual(),
                event.getStockMinimo()
        );
        Integer usuarioIdDefault = 1; // ID de usuario por defecto, cambiar según contexto

        // Usa el email desde configuración
        emailService.enviarEmail(alertaProperties.getEmailDestino(), "⚠️ Alerta de stock bajo", mensaje);

        alertaService.guardarAlerta(new AlertaDto(event.getProductoId(),usuarioIdDefault, mensaje));

        alertaWebSocketHandler.broadcast(mensaje);
    }
}
