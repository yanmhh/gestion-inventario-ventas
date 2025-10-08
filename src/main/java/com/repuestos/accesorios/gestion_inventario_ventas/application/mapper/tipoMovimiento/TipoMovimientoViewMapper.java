package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.TipoMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;

import java.util.Objects;

public class TipoMovimientoViewMapper {
    private TipoMovimientoViewMapper(){}

    public static TipoMovimientoDto from(TipoMovimiento tipoMovimiento){
        Objects.requireNonNull(tipoMovimiento, " El tipo de movimiento no puede ser null.");
        Integer tipoId = tipoMovimiento.getId() != null ? tipoMovimiento.getId().value() : null;

        return new TipoMovimientoDto(
            tipoId,
            tipoMovimiento.getNombre(),
            tipoMovimiento.getDescripcion(),
            tipoMovimiento.getCreadoEn(),
            tipoMovimiento.getActualizadoEn()
    );
    }
}
