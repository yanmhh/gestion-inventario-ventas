package com.repuestos.accesorios.gestion_inventario_ventas.application.listener;

import com.repuestos.accesorios.gestion_inventario_ventas.application.service.notificacion.NotificacionService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajo;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StockBajoListener {
    private final NotificacionService notificacionService;

    public StockBajoListener(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @EventListener
    public void onProductoConStockBajo(StockBajo event) {
        notificacionService.enviarNotificaciones(event);
    }
}
