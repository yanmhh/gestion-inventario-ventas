package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.DetalleProductoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;

import java.util.Objects;

public final class DetalleVentaViewMapper {
    private DetalleVentaViewMapper() {}

    public static DetalleVentaView toView(DetalleVenta detalleVenta) {
        Objects.requireNonNull(detalleVenta, "DetalleVenta no puede ser null");

        DetalleProductoDto productoDto = detalleVenta.getProducto() != null
                ? new DetalleProductoDto(
                detalleVenta.getProducto().getId(),
                detalleVenta.getProducto().getNombre(),
                detalleVenta.getProducto().getCodigo()!= null
                        ? detalleVenta.getProducto().getCodigo().codigo()
                        : null,
                detalleVenta.getPrecioUnitario(),
                detalleVenta.getProducto().getMarca() != null ? detalleVenta.getProducto().getMarca().getNombre() : null,
                detalleVenta.getProducto().getCategoria() != null ? detalleVenta.getProducto().getCategoria().getNombre() : null
        )
                : null;

        return new DetalleVentaView(
                detalleVenta.getId(),
                productoDto,
                detalleVenta.getCantidad(),
                detalleVenta.getPrecioUnitario()
        );
    }
}
