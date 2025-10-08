package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.TipoMovimientoId;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.tipoMovimiento.JpaTipoMovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoMovimientoMapperJpa {
    public TipoMovimiento toDomain(JpaTipoMovimientoEntity entity) {
        if (entity == null) return null;

        return new TipoMovimiento(
                new TipoMovimientoId(entity.getId()),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getCreadoEn(),
                entity.getActualizadoEn()
        );
    }

    public JpaTipoMovimientoEntity toEntity(TipoMovimiento domain) {
        if (domain == null) return null;

        JpaTipoMovimientoEntity entity = new JpaTipoMovimientoEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId().value());
        }
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());
        entity.setCreadoEn(domain.getCreadoEn());
        entity.setActualizadoEn(domain.getActualizadoEn());
        return entity;
    }
}
