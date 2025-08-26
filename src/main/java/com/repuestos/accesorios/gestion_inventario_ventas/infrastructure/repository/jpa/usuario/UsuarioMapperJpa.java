package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.rol.JpaRolEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperJpa {

    public Usuario toDomain(JpaUsuarioEntity jpaUsuarioEntity){
        return new Usuario(
                jpaUsuarioEntity.getId(),
                jpaUsuarioEntity.getNombre(),
                jpaUsuarioEntity.getApellidoPaterno(),
                jpaUsuarioEntity.getApellidoMaterno(),
                jpaUsuarioEntity.getEmail(),
                jpaUsuarioEntity.getPassword(),
                jpaUsuarioEntity.getTelefono(),
                new Rol(jpaUsuarioEntity.getRol().getId(),jpaUsuarioEntity.getRol().getNombre()),
                jpaUsuarioEntity.getCreadoEn(),
                jpaUsuarioEntity.getActualizadoEn()
        );
    }

    public JpaUsuarioEntity toEntity(Usuario usuario){
        JpaUsuarioEntity jpaUsuarioEntity = new JpaUsuarioEntity();
        jpaUsuarioEntity.setId(usuario.getId());
        jpaUsuarioEntity.setNombre(usuario.getNombre());
        jpaUsuarioEntity.setApellidoPaterno(usuario.getApellidoPaterno());
        jpaUsuarioEntity.setApellidoMaterno(usuario.getApellidoMaterno());
        jpaUsuarioEntity.setEmail(usuario.getEmail());
        jpaUsuarioEntity.setPassword(usuario.getPassword());
        jpaUsuarioEntity.setTelefono(usuario.getTelefono());

        JpaRolEntity rol = new JpaRolEntity();
        rol.setId(usuario.getRol().getId());
        rol.setNombre(usuario.getRol().getNombre());
        jpaUsuarioEntity.setRol(rol);

        jpaUsuarioEntity.setCreadoEn(usuario.getCreadoEn());
        jpaUsuarioEntity.setActualizadoEn(usuario.getActualizadoEn());

        return jpaUsuarioEntity;
    }
}
