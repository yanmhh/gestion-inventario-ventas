package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.TipoMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.tipoMovimiento.TipoMovimientoQueryService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MovimientoNoEncontradoException;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-movimiento")
public class TipoMovimientoQueryController {
    private final TipoMovimientoQueryService tipoMovimientoQueryService;

    public TipoMovimientoQueryController(TipoMovimientoQueryService tipoMovimientoQueryService) {
        this.tipoMovimientoQueryService = tipoMovimientoQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable @Positive Integer id) {

        TipoMovimientoDto tipoMovimiento = tipoMovimientoQueryService.obtenerTipoMovimiento(id);
        return ResponseEntity.ok(tipoMovimiento);

    }

    @GetMapping
    public ResponseEntity<List<TipoMovimientoDto>> obtenerTodos() {
        List<TipoMovimientoDto> tipos = tipoMovimientoQueryService.obtenerTodosLosTiposMovimientos();
        return ResponseEntity.ok(tipos);
    }
}
