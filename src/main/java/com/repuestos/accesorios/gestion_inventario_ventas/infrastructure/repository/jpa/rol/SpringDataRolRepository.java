package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.rol;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.rol.JpaRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SpringDataRolRepository extends JpaRepository<JpaRolEntity, Integer> {
    Optional<JpaRolEntity> findByNombre(String nombre);

}
