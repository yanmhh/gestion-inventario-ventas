package com.repuestos.accesorios.gestion_inventario_ventas.application.listener;

import com.repuestos.accesorios.gestion_inventario_ventas.application.service.notificacion.AlertaService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajo;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.email.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductoConStockBajoListener {
    private final AlertaService alertaService;
    private final EmailService emailService;

    public ProductoConStockBajoListener(AlertaService alertaService, EmailService emailService) {
        this.alertaService = alertaService;
        this.emailService = emailService;
    }

    @EventListener
    public void manejar(StockBajo evento) {
        // 1. Guardar alerta
        alertaService.crearAlerta(evento);

        // 2. Enviar correo
        String mensaje = String.format(
                "⚠️ Stock bajo: El producto \"%s\" tiene %d unidades (mínimo permitido: %d)",
                evento.getNombreProducto(), evento.getStockActual(), evento.getStockMinimo()
        );

        emailService.enviarEmail(
                "destinatario@gmail.com",  // ← puedes obtener desde configuración o usuario
                "Alerta de stock bajo",
                mensaje
        );
    }
}
