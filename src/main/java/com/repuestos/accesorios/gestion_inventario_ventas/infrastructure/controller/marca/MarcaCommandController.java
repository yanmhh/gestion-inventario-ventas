package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca.MarcaCommandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/marcas")
public class MarcaCommandController {
    private final MarcaCommandService marcaCommandService;

    public MarcaCommandController(MarcaCommandService marcaCommandService){
        this.marcaCommandService = marcaCommandService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<MarcaDto> registrarMarca (@Valid @RequestBody MarcaDto registerMarcaDto, UriComponentsBuilder uriBuilder){
        MarcaDto marcaCreada = marcaCommandService.registrarMarca(registerMarcaDto);

        URI location = uriBuilder
                .path("/api/marcas/{id}")
                .buildAndExpand(marcaCreada.getId())
                .toUri();

        return ResponseEntity.created(location).body(marcaCreada);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca (@PathVariable @Positive Integer id){
        marcaCommandService.eliminarMarca(id);
        return ResponseEntity.noContent().build();
    }
}
