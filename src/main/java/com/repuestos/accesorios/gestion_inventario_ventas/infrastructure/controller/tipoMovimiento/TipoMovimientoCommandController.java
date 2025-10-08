package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.TipoMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.tipoMovimiento.TipoMovimientoCommandService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MovimientoNoEncontradoException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/tipos-movimiento")
public class TipoMovimientoCommandController {
    private final TipoMovimientoCommandService tipoMovimientoCommandService;

    public TipoMovimientoCommandController(TipoMovimientoCommandService tipoMovimientoCommandService) {
        this.tipoMovimientoCommandService = tipoMovimientoCommandService;
    }

    @PostMapping
    public ResponseEntity<?> registrarTipoMovimiento(@RequestBody @Valid TipoMovimientoDto dto, UriComponentsBuilder uriBuilder) {
        TipoMovimientoDto resultado = tipoMovimientoCommandService.registrarTipoMovimiento(dto);

        URI location = uriBuilder
                .path("/api/tipos-movimiento/{id}")
                .buildAndExpand(resultado.getId())
                .toUri();

        return ResponseEntity.created(location).body(resultado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTipoMovimiento(@PathVariable @Positive Integer id) {
        tipoMovimientoCommandService.eliminarTipoMovimiento(id);
        return ResponseEntity.noContent().build();

    }
}
