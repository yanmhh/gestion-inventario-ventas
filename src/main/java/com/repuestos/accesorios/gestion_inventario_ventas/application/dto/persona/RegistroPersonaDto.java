package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.accesorios.gestion_inventario_ventas.application.shared.Sanitize;

public class RegistroPersonaDto {

    @Sanitize
    private String nombre;

    @Sanitize
    @JsonProperty("apellido_paterno")
    private String apellidoPaterno;

    @Sanitize
    @JsonProperty("apellido_materno")
    private String apellidoMaterno;

    @Sanitize
    private String correo;

    @Sanitize
    private String telefono;

    public RegistroPersonaDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }
}
