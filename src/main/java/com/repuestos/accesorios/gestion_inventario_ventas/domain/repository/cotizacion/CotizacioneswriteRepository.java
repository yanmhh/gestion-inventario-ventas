package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cotizacion;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cotizacion.Cotizacion;

public interface CotizacioneswriteRepository {

    Cotizacion save(Cotizacion cotizacion);

    void deleteById(Integer id);

    Cotizacion update(Cotizacion cotizacion);
}
