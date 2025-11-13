package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.detalleVenta.DetalleVentaQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-ventas")
public class DetalleVentaQueryController {
    private final DetalleVentaQueryService detalleVentaQueryService;

    public DetalleVentaQueryController(DetalleVentaQueryService detalleVentaQueryService) {
        this.detalleVentaQueryService = detalleVentaQueryService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaView>> listarTodos() {
        List<DetalleVentaView> detalles = detalleVentaQueryService.obtenerTodos();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaView> obtenerPorId(@PathVariable @Positive Integer id) {
        DetalleVentaView detalle = detalleVentaQueryService.obtenerPorId(id);
        return ResponseEntity.ok(detalle);
    }

}
