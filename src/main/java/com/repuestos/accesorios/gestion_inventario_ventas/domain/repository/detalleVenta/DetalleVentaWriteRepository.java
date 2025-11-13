package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;

public interface DetalleVentaWriteRepository extends DetalleVentaFinder{

    DetalleVenta save (DetalleVenta detalle);
    DetalleVenta update(DetalleVenta detalle);
    void delete(DetalleVenta detalleVenta);
}
