package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface DetalleVentaRepository {

    Optional<DetalleVenta> findById(Integer id);

    List<DetalleVenta> findAll();
    
    List<DetalleVenta> findByVentaId(Integer ventaId);

    DetalleVenta save(DetalleVenta detalle);
    
    DetalleVenta update(DetalleVenta detalle);
    
    void delete(DetalleVenta detalleVenta);
}
