package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cotizacion;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cotizacion.Cotizacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CotizacionesReadRepository {
    List<Cotizacion> findAll();

    List<Cotizacion> findByFechaEmisionBetween(LocalDateTime inicio, LocalDateTime fin);

    List<Cotizacion> findByEstado(String estado);

    Optional<Cotizacion> findById(Integer id);
}
