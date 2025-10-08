package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;

import java.util.Optional;

public interface MovimientoStockFinder {
    Optional<MovimientoStock> findById(Integer id);
}
