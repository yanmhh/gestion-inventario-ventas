package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {
    Optional<Producto> findById(Integer id);
    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> findByCodigo(String codigo);
    List<Producto> findAll();
    Producto save(Producto producto);
    void deleteById(Integer id);
}
