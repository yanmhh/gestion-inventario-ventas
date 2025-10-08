package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoFilter;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.producto.ProductoQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/productos")
public class ProductoQueryController {

    private final ProductoQueryService productoQueryService;

    public ProductoQueryController(ProductoQueryService productoQueryService){
        this.productoQueryService = productoQueryService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductoView>> listarTodosLosProductos(Pageable pageable) {
        Page<ProductoView> productos = productoQueryService.listarTodosLosProductos(pageable);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoView> obtenerProductoPorId(@PathVariable @Positive Integer id){
        ProductoView producto = productoQueryService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<Page<ProductoView>> buscarPorFiltro(
            @RequestBody ProductoFilter filtro,
            Pageable pageable) {
        Page<ProductoView> productos = productoQueryService.buscarPorFiltro(filtro, pageable);
        return ResponseEntity.ok(productos);
    }
}
