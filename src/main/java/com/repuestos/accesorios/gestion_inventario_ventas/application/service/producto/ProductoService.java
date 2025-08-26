package com.repuestos.accesorios.gestion_inventario_ventas.application.service.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.RegisterProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto.ProductoMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNotFoundException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoYaExisteException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto registrarProducto(RegisterProductoCommand registerRequest){
        validarProduto(registerRequest);

        Producto producto = ProductoMapper.from(registerRequest);
        return productoRepository.save(producto);
    }

    private void validarProduto(RegisterProductoCommand registerRequest){
        productoRepository.findByCodigo(registerRequest.getCodigo())
                .ifPresent(producto ->{
                    throw new ProductoYaExisteException("El código ya esta en uso.");
                });

        if(registerRequest.getPrecio().compareTo(java.math.BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }

        if(registerRequest.getStock() < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto con ID " + id + " no encontrado"));
    }
}
