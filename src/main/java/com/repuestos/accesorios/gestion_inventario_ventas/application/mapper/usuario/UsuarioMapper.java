package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.RegistroUsuarioDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona.PersonaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

public final class UsuarioMapper {

    private UsuarioMapper(){
    }

    public static Usuario from(RegistroUsuarioDto dto,String hash, Rol rol){
        if (dto == null) return null;

        Persona persona = PersonaMapper.toDomain(dto.getPersona());

        return new Usuario(
                null,
                persona,
                hash,
                rol,
                dto.getEstado(),
                LocalDateTime.now()
        );
    }

    public static void mapUpdateData(Usuario usuario, RegistroUsuarioDto dto) {
        if (usuario == null || dto == null) return;

        Persona usuarioActualizada = PersonaMapper.toDomain(dto.getPersona());

        usuario.actualizarUsuario(
                usuarioActualizada,
                dto.getContrasenia(),
                dto.getEstado(),
                LocalDateTime.now()

        );

    }
}
