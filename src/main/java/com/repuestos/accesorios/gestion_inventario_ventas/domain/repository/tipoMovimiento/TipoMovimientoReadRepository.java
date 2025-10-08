package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;

import java.util.List;

public interface TipoMovimientoReadRepository extends TipoMovimientoFinder{
  List<TipoMovimiento> findAll();
}
