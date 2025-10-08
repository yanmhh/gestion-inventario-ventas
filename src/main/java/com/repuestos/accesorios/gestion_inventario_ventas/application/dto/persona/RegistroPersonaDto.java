package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistroPersonaDto {

    private String nombre;

    @JsonProperty("apellido_paterno")
    private String apellidoPaterno;

    @JsonProperty("apellido_materno")
    private String apellidoMaterno;

    private String correo;
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
