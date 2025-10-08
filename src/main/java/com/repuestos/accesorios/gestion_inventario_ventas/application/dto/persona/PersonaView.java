package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona;

import java.time.LocalDateTime;

public class PersonaView {

    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

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

    public PersonaView() {
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
