package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ClienteInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cliente {

    private final Integer id;
    private Persona persona;
    private TipoCliente tipoCliente;
    private String razonSocial;
    private String documentoIdentidad;
    private String rucEmpresa;
    private final LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Cliente(Integer id, Persona persona,TipoCliente tipoCliente, String razonSocial, String documentoIdentidad, String rucEmpresa,
                   LocalDateTime creadoEn) {

        validar(tipoCliente, razonSocial, documentoIdentidad, rucEmpresa);
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

        validar(tipoCliente, razonSocial, documentoIdentidad, rucEmpresa);
        this.persona = persona;
        this.tipoCliente = tipoCliente;
        this.razonSocial = razonSocial;
        this.documentoIdentidad = documentoIdentidad;
        this.rucEmpresa = rucEmpresa;
        this.actualizadoEn = actualizadoEn;
    }

    private void validar(TipoCliente tipoCliente, String razonSocial, String documentoIdentidad, String rucEmpresa) {
        if (tipoCliente == null) {
            throw new ClienteInvalidoException("El tipo de cliente es obligatorio.");
        }

        switch (tipoCliente) {
            case PERSONA_NATURAL:
                if (rucEmpresa != null && !rucEmpresa.isBlank()) {
                    throw new ClienteInvalidoException("Una persona natural no debe tener RUC.");
                }
                validarNoVacio(documentoIdentidad, "La persona natural debe tener documento de identidad.");
                break;

            case EMPRESA:
                validarNoVacio(rucEmpresa, "Una empresa debe tener RUC.");
                validarFormatoRuc(rucEmpresa);
                validarNoVacio(razonSocial, "Una empresa debe tener razón social.");
                break;
        }
    }

    private void validarFormatoRuc(String ruc) {
        if (ruc == null || !ruc.matches("^\\d{11}$")) {
            throw new ClienteInvalidoException("El RUC debe contener exactamente 11 dígitos numéricos.");
        }
    }

    private void validarNoVacio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new ClienteInvalidoException(mensaje);
        }
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
        if (!(o instanceof Cliente cliente)) return false;
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
