package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataRolRepository extends JpaRepository<JpaRolEntity , Integer> {
    Optional<JpaRolEntity> findById (Integer id);
}
