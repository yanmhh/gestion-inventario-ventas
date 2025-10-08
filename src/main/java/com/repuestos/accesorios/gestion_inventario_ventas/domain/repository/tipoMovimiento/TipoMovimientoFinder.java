package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;

import java.util.Optional;

public interface TipoMovimientoFinder {
    Optional<TipoMovimiento> findById(Integer id);
    Optional<TipoMovimiento> findByNombre(String nombre);

}
