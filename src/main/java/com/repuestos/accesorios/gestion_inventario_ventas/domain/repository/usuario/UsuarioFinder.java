package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.util.Optional;

public interface UsuarioFinder {
    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByPersonaId(Integer personaId);
}
