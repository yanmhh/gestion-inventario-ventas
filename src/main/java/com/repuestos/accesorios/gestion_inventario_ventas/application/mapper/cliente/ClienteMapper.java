package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.RegistroClienteDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona.PersonaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.TipoCliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.time.LocalDateTime;


public final class ClienteMapper {

    private ClienteMapper() {
    }

    public static Cliente toDomain(RegistroClienteDto dto) {
        if (dto == null) return null;

        Persona persona = PersonaMapper.toDomain(dto.getPersona());

        return new Cliente(
                null,
                persona,
                dto.getTipoCliente() != null ? TipoCliente.valueOf(dto.getTipoCliente()) : null,
                dto.getRazonSocial(),
                dto.getDocumentoIdentidad(),
                dto.getRucEmpresa(),
                LocalDateTime.now()
        );
    }

    public static void mapUpdateData(Cliente cliente, RegistroClienteDto dto) {
        if (cliente == null || dto == null) return;

        Persona personaActualizada = PersonaMapper.toDomain(dto.getPersona());

        cliente.actualizarCliente(
                personaActualizada,
                dto.getTipoCliente() != null ? TipoCliente.valueOf(dto.getTipoCliente()) : null,
                dto.getRazonSocial(),
                dto.getDocumentoIdentidad(),
                dto.getRucEmpresa(),
                LocalDateTime.now()
        );
    }
}
