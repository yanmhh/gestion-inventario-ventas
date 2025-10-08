package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.categoria.CategoriaQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaQueryController {

    private final CategoriaQueryService categoriaQueryService;

    public CategoriaQueryController(CategoriaQueryService categoriaQueryService){
        this.categoriaQueryService = categoriaQueryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDto>> listarTodosLasCategorias(Pageable pageable){
        Page<CategoriaDto> categorias = categoriaQueryService.listarTodosLasCategorias(pageable);
        return ResponseEntity.ok(categorias);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> obtenerCategoriaPorId(@PathVariable @Positive Integer id){
        CategoriaDto categoria = categoriaQueryService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }
}
