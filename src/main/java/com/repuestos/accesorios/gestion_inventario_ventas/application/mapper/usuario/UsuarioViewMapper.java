package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona.PersonaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.util.Objects;

public final class UsuarioViewMapper {
    private UsuarioViewMapper(){
    }

    public static UsuarioView toView(Usuario usuario){
        Objects.requireNonNull(usuario, "El usuario no puede ser null.");
        return new UsuarioView(
                usuario.getId(),
                PersonaViewMapper.toView(usuario.getPersona()),
                usuario.getRol(),
                usuario.getEstado() != null ? usuario.getEstado().name() : null,
                usuario.getCreadoEn(),
                usuario.getActualizadoEn()
        );
    }
}
