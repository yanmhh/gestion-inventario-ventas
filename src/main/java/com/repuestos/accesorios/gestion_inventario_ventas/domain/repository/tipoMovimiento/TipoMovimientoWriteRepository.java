package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;

public interface TipoMovimientoWriteRepository extends TipoMovimientoFinder {
    TipoMovimiento save(TipoMovimiento tipoMovimiento);
    void deleteById(Integer id);
}
