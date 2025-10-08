package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.RegisterProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.UpdateProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.producto.ProductoCommandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/productos")
public class ProductoCommandController {

    private final ProductoCommandService productoCommandService;

    public  ProductoCommandController(ProductoCommandService productoCommandService){
        this.productoCommandService = productoCommandService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<ProductoView> registrarProducto(@RequestBody @Valid RegisterProductoCommand registerRequest, UriComponentsBuilder uriBuilder){

        ProductoView productoCreado = productoCommandService.registrarProducto(registerRequest);

        URI location = uriBuilder
                .path("/api/productos/{id}")
                .buildAndExpand(productoCreado.getId())
                .toUri();

        return ResponseEntity.created(location).body(productoCreado);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoView> actualizarProducto(@PathVariable Integer id,@Valid @RequestBody UpdateProductoCommand updateRequest){
        ProductoView productoActualizado = productoCommandService.actualizarProducto(id, updateRequest);
        return ResponseEntity.ok(productoActualizado);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable @Positive Integer id){
        productoCommandService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
