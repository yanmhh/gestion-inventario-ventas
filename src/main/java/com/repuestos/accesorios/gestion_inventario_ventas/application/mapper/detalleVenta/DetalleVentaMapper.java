package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.RegistroDetalleVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;

import java.math.BigDecimal;

public final class DetalleVentaMapper {

    private DetalleVentaMapper() {}

    public static DetalleVenta from(RegistroDetalleVentaDto dto, Producto producto) {
        if (dto == null || producto == null) {
            throw new IllegalArgumentException("RegistroDetalleVentaDto y Producto no pueden ser null");
        }

        return new DetalleVenta(
                null,
                producto,
                dto.getCantidad(),
                dto.getPrecioUnitario() != null ? dto.getPrecioUnitario() : BigDecimal.ZERO

        );

    }
}
