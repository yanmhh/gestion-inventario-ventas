package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta.AlertaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.alerta.AlertaCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaCommandController {

    private final AlertaCommandService alertaCommandService;

    public AlertaCommandController(AlertaCommandService alertaCommandService) {
        this.alertaCommandService = alertaCommandService;
    }

    @PutMapping("/{alertaId}/marcar-leida")
    public ResponseEntity<AlertaView> marcarComoLeida(@PathVariable Integer alertaId) {
        AlertaView alertaView = alertaCommandService.marcarComoLeida(alertaId);
        return ResponseEntity.ok(alertaView);
    }
}
