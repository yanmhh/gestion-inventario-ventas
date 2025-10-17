package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.RegistroPersonaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.time.LocalDateTime;

public final class PersonaMapper {

    private PersonaMapper() {
    }

    public static Persona toDomain(RegistroPersonaDto registroPersonaDto) {
        return new Persona(
                null,
                registroPersonaDto.getNombre(),
                registroPersonaDto.getApellidoPaterno(),
                registroPersonaDto.getApellidoMaterno(),
                registroPersonaDto.getCorreo(),
                registroPersonaDto.getTelefono(),
                LocalDateTime.now(),
                LocalDateTime.now()
                );
    }

    public static void mapUpdateData(RegistroPersonaDto dto, Persona persona) {
        persona.actualizarPersona(
                dto.getNombre(),
                dto.getApellidoPaterno(),
                dto.getApellidoMaterno(),
                dto.getCorreo(),
                dto.getTelefono(),
                LocalDateTime.now()
        );
    }
}
