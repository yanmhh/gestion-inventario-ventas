package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;

import java.util.Optional;

public interface DetalleVentaFinder {

    Optional<DetalleVenta> findById(Integer id);
}
