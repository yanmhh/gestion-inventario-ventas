package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.persona.JpaPersonaEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapperJpa {

    private PersonaMapperJpa() {
    }

    public Persona toDomain(JpaPersonaEntity jpaPersonaEntity) {
        if (jpaPersonaEntity == null) {
            return null;
        }

        return new Persona(
                jpaPersonaEntity.getId(),
                jpaPersonaEntity.getNombre(),
                jpaPersonaEntity.getApellidoPaterno(),
                jpaPersonaEntity.getApellidoMaterno(),
                jpaPersonaEntity.getCorreo(),
                jpaPersonaEntity.getTelefono(),
                jpaPersonaEntity.getCreadoEn(),
                jpaPersonaEntity.getActualizadoEn()
        );
    }

    public JpaPersonaEntity toEntity(Persona persona) {
        if (persona == null) {
            return null;
        }

        JpaPersonaEntity jpaPersonaEntity = new JpaPersonaEntity();
        jpaPersonaEntity.setId(persona.getId());
        jpaPersonaEntity.setNombre(persona.getNombre());
        jpaPersonaEntity.setApellidoPaterno(persona.getApellidoPaterno());
        jpaPersonaEntity.setApellidoMaterno(persona.getApellidoMaterno());
        jpaPersonaEntity.setCorreo(persona.getCorreo());
        jpaPersonaEntity.setTelefono(persona.getTelefono());
        jpaPersonaEntity.setCreadoEn(persona.getCreadoEn());
        jpaPersonaEntity.setActualizadoEn(persona.getActualizadoEn());
        return jpaPersonaEntity;
    }
}
