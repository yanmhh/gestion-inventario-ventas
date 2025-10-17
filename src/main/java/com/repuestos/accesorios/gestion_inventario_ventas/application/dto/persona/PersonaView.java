package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PersonaView {

    private final Integer id;
    private final String nombre;
    @JsonProperty("apellido_paterno")
    private final String apellidoPaterno;

    @JsonProperty("apellido_materno")
    private final String apellidoMaterno;

    private final String correo;
    private final String telefono;
    @JsonProperty("creado_en")
    private final LocalDateTime creadoEn;

    @JsonProperty("actualizado_en")
    private final LocalDateTime actualizadoEn;

    public PersonaView(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
                       String telefono, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return id;
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

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
