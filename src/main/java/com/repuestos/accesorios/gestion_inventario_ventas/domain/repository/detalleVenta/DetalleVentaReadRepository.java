package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoFinder;

import java.util.List;

public interface DetalleVentaReadRepository extends DetalleVentaFinder {

    List<DetalleVenta> findAll();
    List<DetalleVenta> findByVentaId(Integer ventaId);
}
