package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.movimientoStock.JpaTipoMovimientoEntity;
import org.springframework.stereotype.Component;


@Component
public class TipoMovimientoMapperJpa {
    public TipoMovimiento toDomain(JpaTipoMovimientoEntity entity) {
        if (entity == null) return null;

        return switch (entity) {
            case INGRESO -> TipoMovimiento.ENTRADA;
            case SALIDA -> TipoMovimiento.SALIDA;
            case DEVOLUCION_CLIENTE -> TipoMovimiento.DEVOLUCION_CLIENTE;
            case DEVOLUCION_PROVEEDOR -> TipoMovimiento.DEVOLUCION_PROVEEDOR;
        };
    }

    public JpaTipoMovimientoEntity toEntity(TipoMovimiento domain) {
        if (domain == null) return null;

        return switch (domain) {
            case ENTRADA -> JpaTipoMovimientoEntity.INGRESO;
            case SALIDA -> JpaTipoMovimientoEntity.SALIDA;
            case DEVOLUCION_CLIENTE -> JpaTipoMovimientoEntity.DEVOLUCION_CLIENTE;
            case DEVOLUCION_PROVEEDOR -> JpaTipoMovimientoEntity.DEVOLUCION_PROVEEDOR;
        };
    }
}
