package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.util.List;

public interface UsuarioReadRepository extends UsuarioFinder{
    List<Usuario> findAll();
    boolean existsById(Integer id);
}
