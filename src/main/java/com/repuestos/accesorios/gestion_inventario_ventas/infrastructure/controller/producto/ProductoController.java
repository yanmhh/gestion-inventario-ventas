package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.RegisterProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.producto.ProductoService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@RequestBody RegisterProductoCommand registerRequest){

        Producto productoCreado = productoService.registrarProducto(registerRequest);

        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id){
        Producto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }
}
