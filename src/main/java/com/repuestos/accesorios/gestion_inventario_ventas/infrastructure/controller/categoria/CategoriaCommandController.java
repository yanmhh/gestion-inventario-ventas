package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.categoria.CategoriaCommandService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaCommandController {

    private final CategoriaCommandService categoriaCommandService;

    public CategoriaCommandController(CategoriaCommandService categoriaCommandService){
        this.categoriaCommandService = categoriaCommandService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<CategoriaDto> registrarCategoria (@Valid @RequestBody CategoriaDto registerMarcaDto){
        CategoriaDto categoriaCreado = categoriaCommandService.registrarCategoria(registerMarcaDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriaCreado.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoriaCreado);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id){
        categoriaCommandService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
