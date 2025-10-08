package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;

public interface MovimientoStockWriteRepository extends MovimientoStockFinder {
    MovimientoStock save(MovimientoStock movimientoStock);
    void deleteById(Integer id);
}
