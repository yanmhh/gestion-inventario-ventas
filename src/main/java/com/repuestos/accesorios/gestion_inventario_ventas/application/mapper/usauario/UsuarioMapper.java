package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.usauario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.RegisterUserCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

public class UsuarioMapper {

    public static Usuario from(RegisterUserCommand registerRequest, Rol rol, String password){
        return new Usuario(
                null,
                registerRequest.getNombre(),
                registerRequest.getApellidoPaterno(),
                registerRequest.getApellidoMaterno(),
                registerRequest.getEmail(),
                password,
                registerRequest.getTelefono(),
                rol,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
