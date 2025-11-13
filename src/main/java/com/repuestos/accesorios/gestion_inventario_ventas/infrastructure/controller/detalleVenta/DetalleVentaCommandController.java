package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.RegistroDetalleVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.detalleVenta.DetalleVentaCommandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/detalle-ventas")
public class DetalleVentaCommandController {
    private final DetalleVentaCommandService detalleVentaCommandService;

    public DetalleVentaCommandController(DetalleVentaCommandService detalleVentaCommandService) {
        this.detalleVentaCommandService = detalleVentaCommandService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<DetalleVentaView> registrarDetalle(
            @RequestBody @Valid RegistroDetalleVentaDto registroDetalleVentaDto,
            UriComponentsBuilder uriBuilder) {

        DetalleVentaView detalleCreado = detalleVentaCommandService.registrarDetalle(registroDetalleVentaDto);

        URI location = uriBuilder
                .path("/api/detalle-ventas/{id}")
                .buildAndExpand(detalleCreado.getId())
                .toUri();

        return ResponseEntity.created(location).body(detalleCreado);
    }

}
