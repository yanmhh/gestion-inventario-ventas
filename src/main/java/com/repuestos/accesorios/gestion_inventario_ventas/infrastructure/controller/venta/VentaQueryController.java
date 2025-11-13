package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.VentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.venta.VentaQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ventas/query")
public class VentaQueryController {
    private final VentaQueryService ventaQueryService;

    public VentaQueryController(VentaQueryService ventaQueryService) {
        this.ventaQueryService = ventaQueryService;
    }

    @GetMapping
    public ResponseEntity<List<VentaView>> listarTodas() {
        List<VentaView> ventas = ventaQueryService.obtenerTodas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaView> obtenerPorId(@PathVariable @Positive Integer id) {
        VentaView venta = ventaQueryService.obtenerPorId(id);
        return ResponseEntity.ok(venta);
    }
}
