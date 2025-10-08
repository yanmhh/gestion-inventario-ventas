package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.PersonaView;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;

import java.time.LocalDateTime;

public class UsuarioView {

    private final Integer id;
    private PersonaView persona;
    private Rol rol;
    private String estado;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public UsuarioView(Integer id, PersonaView persona , Rol rol, String estado,LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        this.id = id;
        this.persona = persona;
        this.rol = rol;
        this.estado = estado;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return id;
    }

    public PersonaView getPersona() {
        return persona;
    }

    public Rol getRol() {
        return rol;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
