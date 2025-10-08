package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.movimientoStock.MovimientoStockCommandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/movimientos-stock")
public class MovimientoStockCommandController {
    private final MovimientoStockCommandService movimientoStockCommandService;

    public MovimientoStockCommandController(MovimientoStockCommandService movimientoStockCommandService) {
        this.movimientoStockCommandService = movimientoStockCommandService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'ALMACENERO')")
    @PostMapping
    public ResponseEntity<MovimientoStockView> registrarMovimiento(@RequestBody @Valid RegistroMovimientoDto dto, UriComponentsBuilder uriBuilder) {
        MovimientoStockView movimientoCreado = movimientoStockCommandService.registrarMovimiento(dto);
        URI location = uriBuilder
                .path("/api/movimientos-stock/{id}")
                .buildAndExpand(movimientoCreado.getId())
                .toUri();

        return ResponseEntity.created(location).body(movimientoCreado);
    }
}
