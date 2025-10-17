package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.categoria.CategoriaQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorias/query")
public class CategoriaQueryController {

    private final CategoriaQueryService categoriaQueryService;

    public CategoriaQueryController(CategoriaQueryService categoriaQueryService){
        this.categoriaQueryService = categoriaQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaView>> listarTodosLasCategorias(){
        List<CategoriaView> categorias = categoriaQueryService.listarTodosLasCategorias();
        return ResponseEntity.ok(categorias);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaView> obtenerCategoriaPorId(@PathVariable @Positive Integer id){
        CategoriaView categoria = categoriaQueryService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }
}
