package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajo;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class NotificacionTestController {
    private final ApplicationEventPublisher publisher;

    public NotificacionTestController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/alerta")
    public ResponseEntity<String> enviarAlertaDePrueba() {
        StockBajo evento = new StockBajo(
                1, "Aceite Motor", 2, 5
        );
        publisher.publishEvent(evento);
        return ResponseEntity.ok("Evento de alerta de stock bajo enviado");
    }
}
