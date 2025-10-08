package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

public interface UsuarioWriteRepository extends UsuarioFinder{
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
}
