package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cliente {

    private final Integer id;
    private  Persona persona;
    private  TipoCliente tipoCliente;
    private String razonSocial;
    private String documentoIdentidad;
    private String rucEmpresa;
    private final LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Cliente(Integer id, Persona persona,TipoCliente tipoCliente, String razonSocial, String documentoIdentidad, String rucEmpresa,
                   LocalDateTime creadoEn) {

        TipoCliente.validarTipo(tipoCliente, razonSocial, documentoIdentidad, rucEmpresa);

        this.id = id;
        this.persona = persona;
        this.tipoCliente = tipoCliente;
        this.razonSocial = razonSocial;
        this.documentoIdentidad = documentoIdentidad;
        this.rucEmpresa = rucEmpresa;
        this.creadoEn = creadoEn;
    }

    public void actualizarCliente( Persona persona,TipoCliente tipoCliente, String razonSocial, String documentoIdentidad, String rucEmpresa
                   , LocalDateTime actualizadoEn) {

        TipoCliente.validarTipo(tipoCliente, razonSocial, documentoIdentidad, rucEmpresa);

        this.persona = persona;
        this.tipoCliente = tipoCliente;
        this.razonSocial = razonSocial;
        this.documentoIdentidad = documentoIdentidad;
        this.rucEmpresa = rucEmpresa;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public TipoCliente getTipoCliente() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", persona=" + persona +
                ", tipoCliente=" + tipoCliente +
                ", razonSocial='" + razonSocial + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                ", rucEmpresa='" + rucEmpresa + '\'' +
                ", creadoEn=" + creadoEn +
                ", actualizadoEn=" + actualizadoEn +
                '}';
    }
}
