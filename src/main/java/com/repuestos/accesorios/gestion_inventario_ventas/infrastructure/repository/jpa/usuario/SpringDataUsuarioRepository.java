package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.usuario.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUsuarioRepository extends JpaRepository<JpaUsuarioEntity,Integer> {
    Optional<JpaUsuarioEntity> findByPersonaId(Integer personaId);

}
