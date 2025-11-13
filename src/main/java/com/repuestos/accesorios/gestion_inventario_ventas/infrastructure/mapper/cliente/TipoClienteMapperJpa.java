package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.TipoCliente;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.cliente.TipoClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoClienteMapperJpa {
    public TipoCliente toDomain(TipoClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return switch (entity) {
            case PERSONA_NATURAL -> TipoCliente.PERSONA_NATURAL;
            case EMPRESA -> TipoCliente.EMPRESA;
        };
    }

    public TipoClienteEntity toEntity(TipoCliente domain) {
        if (domain == null) {
            return null;
        }
        return switch (domain) {
            case PERSONA_NATURAL -> TipoClienteEntity.PERSONA_NATURAL;
            case EMPRESA -> TipoClienteEntity.EMPRESA;
        };
    }
}
