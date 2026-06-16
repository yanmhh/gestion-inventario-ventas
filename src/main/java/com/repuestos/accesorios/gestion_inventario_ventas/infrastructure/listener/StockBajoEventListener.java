package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.listener;

import com.repuestos.accesorios.gestion_inventario_ventas.application.service.alerta.StockAlertaService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StockBajoEventListener {

    private static final Logger log = LoggerFactory.getLogger(StockBajoEventListener.class);

    private final StockAlertaService stockAlertaService;

    public StockBajoEventListener(StockAlertaService stockAlertaService) {
        this.stockAlertaService = stockAlertaService;
    }

    @Async
    @EventListener
    public void manejarStockBajo(StockBajoEvent event) {
        log.info("Evento de stock bajo recibido para producto: {}", event.getProducto().getNombre());
        stockAlertaService.procesarAlertaStockBajo(event);
    }
}
