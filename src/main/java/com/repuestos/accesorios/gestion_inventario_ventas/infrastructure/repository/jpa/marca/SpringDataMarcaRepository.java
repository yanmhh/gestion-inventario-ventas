package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.marca.JpaMarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataMarcaRepository  extends JpaRepository<JpaMarcaEntity, Integer> {

    Optional<JpaMarcaEntity> findById(Integer id);
    Optional<JpaMarcaEntity> findByNombre(String nombre);

}
