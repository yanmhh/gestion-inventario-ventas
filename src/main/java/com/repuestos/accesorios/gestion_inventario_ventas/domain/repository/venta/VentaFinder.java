package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.EstadoVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaFinder {

    Optional<Venta> findById(Integer id);
    List<Venta> findByEstado(EstadoVenta estado);
    List<Venta> findByCliente(Integer clienteId);
}
