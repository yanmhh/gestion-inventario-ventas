package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.TipoMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;

public class TipoMovimientoMapper {
    private TipoMovimientoMapper(){
    }
    public static TipoMovimiento from(TipoMovimientoDto registerTipoMovimientoDto) {
        return new TipoMovimiento(
                null,
                registerTipoMovimientoDto.getNombre(),
                registerTipoMovimientoDto.getDescripcion(),
                registerTipoMovimientoDto.getCreadoEn(),
                registerTipoMovimientoDto.getActualizadoEn()
        );
    }
}
