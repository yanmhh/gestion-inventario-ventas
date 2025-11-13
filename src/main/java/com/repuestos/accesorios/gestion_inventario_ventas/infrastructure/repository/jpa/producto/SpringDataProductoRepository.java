package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataProductoRepository extends JpaRepository<JpaProductoEntity, Integer> , JpaSpecificationExecutor<JpaProductoEntity> {

    Optional<JpaProductoEntity> findByNombre(String nombre);
    Optional<JpaProductoEntity> findByCodigo(String codigo);
}
