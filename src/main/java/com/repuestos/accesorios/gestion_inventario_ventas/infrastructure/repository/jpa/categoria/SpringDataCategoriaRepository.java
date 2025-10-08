package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.categoria.JpaCategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataCategoriaRepository extends JpaRepository<JpaCategoriaEntity, Integer> {

    Optional<JpaCategoriaEntity> findById(Integer id);
    Optional<JpaCategoriaEntity> findByNombre(String nombre);
}
