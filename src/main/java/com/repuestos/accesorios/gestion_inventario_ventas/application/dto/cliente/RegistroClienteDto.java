package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.RegistroPersonaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.shared.Sanitize;

public class RegistroClienteDto {

    private RegistroPersonaDto persona;

    @Sanitize
    @JsonProperty("tipo_cliente")
    private String tipoCliente;

    @Sanitize
    @JsonProperty("razon_social")
    private String razonSocial;

    @Sanitize
    @JsonProperty("documento_identidad")
    private String documentoIdentidad;

    @Sanitize
    @JsonProperty("ruc_empresa")
    private String rucEmpresa;

    public RegistroClienteDto() {
    }

    public RegistroPersonaDto getPersona() {
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
}
