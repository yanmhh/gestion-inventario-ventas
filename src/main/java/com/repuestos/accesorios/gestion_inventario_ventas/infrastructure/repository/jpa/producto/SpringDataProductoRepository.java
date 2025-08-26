package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataProductoRepository extends JpaRepository<JpaProductoEntity, Integer> {

    Optional<JpaProductoEntity> findByNombre(String nombre);
    Optional<JpaProductoEntity> findByCodigo(String codigo);
}
