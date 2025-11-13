package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;

import java.util.List;

public interface VentaReadRepository extends VentaFinder {

    List<Venta> findAll();
    boolean existePorId(Integer id);
}
