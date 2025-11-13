package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.EstadoVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.EstadoVentaEntity;
import org.springframework.stereotype.Component;

@Component
public class EstadoVentaMapperJpa {

    public EstadoVenta toDomain(EstadoVentaEntity entity) {
        if (entity == null) return null;
        return switch (entity) {
            case PENDIENTE -> EstadoVenta.PENDIENTE;
            case CONFIRMADA -> EstadoVenta.CONFIRMADA;
            case ANULADA -> EstadoVenta.ANULADA;
        };
    }

    public EstadoVentaEntity toEntity(EstadoVenta domain) {
        if (domain == null) return null;
        return switch (domain) {
            case PENDIENTE -> EstadoVentaEntity.PENDIENTE;
            case CONFIRMADA -> EstadoVentaEntity.CONFIRMADA;
            case ANULADA -> EstadoVentaEntity.ANULADA;
        };
    }
}
