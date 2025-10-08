package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cotizacion;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cotizacion.Cotizacion;

import java.util.Optional;

public interface CotizacionesFinder {

    Optional<Cotizacion> findById(Integer id);

    Optional<Cotizacion> findByClienteId(Integer clienteId);

    Optional<Cotizacion> findByNumero(String numero);
}
