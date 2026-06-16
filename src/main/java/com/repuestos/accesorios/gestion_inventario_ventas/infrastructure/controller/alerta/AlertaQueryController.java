package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta.AlertaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.alerta.AlertaQueryService;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaQueryController {

    private final AlertaQueryService alertaQueryService;

    public AlertaQueryController(AlertaQueryService alertaQueryService) {
        this.alertaQueryService = alertaQueryService;
    }

    @GetMapping("/mis-alertas")
    public ResponseEntity<List<AlertaView>> obtenerMisAlertas(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<AlertaView> alertas = alertaQueryService.obtenerAlertasPorUsuario(userDetails.getUsuario().getId());
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/mis-alertas/no-leidas")
    public ResponseEntity<List<AlertaView>> obtenerMisAlertasNoLeidas(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<AlertaView> alertas = alertaQueryService.obtenerAlertasNoLeidasPorUsuario(userDetails.getUsuario().getId());
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/todas/no-leidas")
    public ResponseEntity<List<AlertaView>> obtenerTodasAlertasNoLeidas() {
        List<AlertaView> alertas = alertaQueryService.obtenerTodasLasAlertasNoLeidas();
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AlertaView>> obtenerAlertasPorUsuario(
            @PathVariable Integer usuarioId) {
        List<AlertaView> alertas = alertaQueryService.obtenerAlertasPorUsuario(usuarioId);
        return ResponseEntity.ok(alertas);
    }
}
