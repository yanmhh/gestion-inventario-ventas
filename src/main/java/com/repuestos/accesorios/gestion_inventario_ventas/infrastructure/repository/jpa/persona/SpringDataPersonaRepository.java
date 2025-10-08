package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.persona.JpaPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataPersonaRepository extends JpaRepository<JpaPersonaEntity ,Integer> {
    Optional<JpaPersonaEntity> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
