package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByPersonaId(Integer personaId);
    boolean existsById(Integer id);
    
    List<Usuario> findAll();
    
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
}
