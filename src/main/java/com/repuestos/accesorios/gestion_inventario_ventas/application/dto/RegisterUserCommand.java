package com.repuestos.accesorios.gestion_inventario_ventas.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserCommand {

    private String nombre;

    @JsonProperty("apellido_paterno")
    private String apellidoPaterno;

    @JsonProperty("apellido_materno")
    private String apellidoMaterno;

    private String email;
    private String password;
    private String telefono;

    @JsonProperty("rolId")
    private Integer rolId;

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Integer getRolId() {
        return this.rolId;
    }

}
