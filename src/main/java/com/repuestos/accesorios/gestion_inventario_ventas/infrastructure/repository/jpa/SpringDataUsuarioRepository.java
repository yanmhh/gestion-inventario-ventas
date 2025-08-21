package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUsuarioRepository extends JpaRepository<JpaUsuarioEntity,Integer> {

    Optional<JpaUsuarioEntity> findByEmail(String email);
}
