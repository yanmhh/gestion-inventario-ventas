package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.movimientoStock.MovimientoStockQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos-stock/query")
public class MovimientoStockQueryController {

    private final MovimientoStockQueryService movimientoStockQueryService;

    public MovimientoStockQueryController(MovimientoStockQueryService movimientoStockQueryService) {
        this.movimientoStockQueryService = movimientoStockQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMovimientoPorId(@PathVariable @Positive Integer id) {
        MovimientoStockView movimiento = movimientoStockQueryService.obtenerPorId(id);
            return ResponseEntity.ok(movimiento);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoStockView>> obtenerTodosLosMovimientos() {
        List<MovimientoStockView> movimientos = movimientoStockQueryService.obtenerTodosLosMovimientos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<MovimientoStockView>> obtenerMovimientosPorProducto(@PathVariable @Positive Integer productoId) {
        List<MovimientoStockView> movimientos = movimientoStockQueryService.obtenerMovimientosPorProductoId(productoId);
        return ResponseEntity.ok(movimientos);
    }
}
