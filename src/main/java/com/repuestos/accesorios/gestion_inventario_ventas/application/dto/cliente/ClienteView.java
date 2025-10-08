package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.PersonaView;

import java.time.LocalDateTime;

public class ClienteView {

    private final Integer id;
    private PersonaView persona;
    private String tipoCliente;
    private String razonSocial;
    private String documentoIdentidad;
    private String rucEmpresa;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public ClienteView(Integer id, PersonaView persona, String tipoCliente, String razonSocial, String documentoIdentidad, String rucEmpresa, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        this.id = id;
        this.persona = persona;
        this.tipoCliente = tipoCliente;
        this.razonSocial = razonSocial;
        this.documentoIdentidad = documentoIdentidad;
        this.rucEmpresa = rucEmpresa;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return id;
    }

    public PersonaView getPersona() {
        return persona;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
