package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;

import java.util.List;
import java.util.Optional;


public interface MovimientoStockRepository {


    Optional<MovimientoStock> findById(Integer id);

    List<MovimientoStock> findAll();

    List<MovimientoStock> findByProductoId(Integer productoId);

    MovimientoStock save(MovimientoStock movimientoStock);

    void deleteById(Integer id);
}
