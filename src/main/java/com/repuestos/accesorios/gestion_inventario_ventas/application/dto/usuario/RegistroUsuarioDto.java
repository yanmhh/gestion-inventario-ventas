package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.RegistroPersonaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.shared.Sanitize;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.EstadoUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
public class RegistroUsuarioDto {

    @Valid
    @NotNull(message = "La persona es obligatoria")
    private RegistroPersonaDto persona;

    @Sanitize
    @NotNull(message = "La contraseña es obligatoria")
    private String contrasenia;

    @NotNull(message = "El rol es obligatorio")
    private Integer rolId;

    @NotNull(message = "El estado del usuario es obligatorio")
    private EstadoUsuario estado;

    public RegistroUsuarioDto() {
    }

    public RegistroPersonaDto getPersona() {
        return persona;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Integer getRolId() {
        return rolId;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

}
