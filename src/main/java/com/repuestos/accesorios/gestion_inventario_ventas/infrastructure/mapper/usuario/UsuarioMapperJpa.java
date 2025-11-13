package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.rol.JpaRolEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.usuario.JpaUsuarioEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.persona.PersonaMapperJpa;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapperJpa {

    private final PersonaMapperJpa personaMapperJpa;


    public UsuarioMapperJpa(PersonaMapperJpa personaMapperJpa) {
        this.personaMapperJpa = personaMapperJpa;
    }

    public Usuario toDomain(JpaUsuarioEntity jpaUsuarioEntity){
        if (jpaUsuarioEntity == null) {
            return null;
        }
        return new Usuario(
                jpaUsuarioEntity.getId(),
                personaMapperJpa.toDomain(jpaUsuarioEntity.getPersona()),
                jpaUsuarioEntity.getContrasenia(),
                new Rol(
                        jpaUsuarioEntity.getRol().getId(),
                        jpaUsuarioEntity.getRol().getNombre()),
                jpaUsuarioEntity.getEstado(),
                jpaUsuarioEntity.getCreadoEn()
        );
    }

    public JpaUsuarioEntity toEntity(Usuario usuario){
            if (usuario == null) {
                return null;
            }
        JpaUsuarioEntity jpaUsuarioEntity = new JpaUsuarioEntity();
        jpaUsuarioEntity.setId(usuario.getId());
        jpaUsuarioEntity.setPersona(personaMapperJpa.toEntity(usuario.getPersona()));
        jpaUsuarioEntity.setContrasenia(usuario.getContrasenia());

        if (usuario.getRol() != null) {
            JpaRolEntity rol = new JpaRolEntity();
            rol.setId(usuario.getRol().getId());
            jpaUsuarioEntity.setRol(rol);
        }

        jpaUsuarioEntity.setEstado(usuario.getEstado());
        jpaUsuarioEntity.setCreadoEn(usuario.getCreadoEn());
        jpaUsuarioEntity.setActualizadoEn(usuario.getActualizadoEn());

        return jpaUsuarioEntity;
    }
}
