package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperJpa {
    public Usuario toDomain(JpaUsuarioEntity entity){
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getApellidoPaterno(),
                entity.getApellidoMaterno(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getTelefono(),
                new Rol(entity.getRol().getId(),entity.getRol().getNombre()),
                entity.getCreadoEn(),
                entity.getActualizadoEn()
        );
    }

    public JpaUsuarioEntity toEntity(Usuario usuario){
        JpaUsuarioEntity entity = new JpaUsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setApellidoPaterno(usuario.getApellidoPaterno());
        entity.setApellidoMaterno(usuario.getApellidoMaterno());
        entity.setEmail(usuario.getEmail());
        entity.setPassword(usuario.getPassword());
        entity.setTelefono(usuario.getTelefono());

        JpaRolEntity rol = new JpaRolEntity();
        rol.setId(usuario.getRol().getId());
        rol.setNombre(usuario.getRol().getNombre());
        entity.setRol(rol);

        entity.setCreadoEn(usuario.getCreadoEn());
        entity.setActualizadoEn(usuario.getActualizadoEn());

        return entity;
    }
}
