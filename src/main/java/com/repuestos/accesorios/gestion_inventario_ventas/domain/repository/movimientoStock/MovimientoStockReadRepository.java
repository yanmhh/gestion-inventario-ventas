package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import java.util.List;


public interface MovimientoStockReadRepository extends MovimientoStockFinder{
    List<MovimientoStock> findAll();
    List<MovimientoStock> findByProductoId(Integer productoId);


}
