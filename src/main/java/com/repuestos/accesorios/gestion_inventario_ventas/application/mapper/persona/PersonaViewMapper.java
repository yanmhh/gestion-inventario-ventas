package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.PersonaView;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.util.Objects;

public final class PersonaViewMapper {
    private PersonaViewMapper() {
    }

    public static PersonaView toView(Persona persona) {
        Objects.requireNonNull(persona, "La persona no puede ser null.");

        return new PersonaView(
                persona.getId(),
                persona.getNombre(),
                persona.getApellidoPaterno(),
                persona.getApellidoMaterno(),
                persona.getCorreo(),
                persona.getTelefono(),
                persona.getCreadoEn(),
                persona.getActualizadoEn()
        );
    }
}
