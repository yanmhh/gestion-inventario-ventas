package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.ClienteView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona.PersonaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;

import java.util.Objects;


public final class ClienteViewMapper {

    private ClienteViewMapper() {
    }

    public static ClienteView toView(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser null.");

        return new ClienteView(
                cliente.getId(),
                PersonaViewMapper.toView(cliente.getPersona()),
                cliente.getTipoCliente() != null ? cliente.getTipoCliente().name() : null,
                cliente.getRazonSocial(),
                cliente.getDocumentoIdentidad(),
                cliente.getRucEmpresa(),
                cliente.getCreadoEn(),
                cliente.getActualizadoEn()
        );
    }
}
