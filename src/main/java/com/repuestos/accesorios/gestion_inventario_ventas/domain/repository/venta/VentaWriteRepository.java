package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;

public interface VentaWriteRepository extends VentaFinder {

    Venta save(Venta venta);
    void delete(Integer id);

}
