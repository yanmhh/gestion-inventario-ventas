package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.RegistroPersonaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.time.LocalDateTime;

public class PersonaMapper {
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
}
