package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoResumenDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioResumenDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;

import java.util.Objects;

public final class MovimientoStockViewMapper {
    private MovimientoStockViewMapper() {
    }

    public static MovimientoStockView toView(MovimientoStock movimientoStock) {
        Objects.requireNonNull(movimientoStock, "El movimiento de stock no puede ser null.");


        ProductoResumenDto productoDto = movimientoStock.getProducto() != null
                ? new ProductoResumenDto(
                movimientoStock.getProducto().getId(),
                movimientoStock.getProducto().getNombre())
                : null;

        UsuarioResumenDto usuarioDto = movimientoStock.getUsuario() != null
                ? new UsuarioResumenDto(
                movimientoStock.getUsuario().getId(),
                movimientoStock.getUsuario().getPersona().getNombre())
                : null;

        String tipoMovimientoStr = movimientoStock.getTipoMovimiento() != null
                ? movimientoStock.getTipoMovimiento().name()  // Nombre del enum
                : null;

        Integer movimientoId = movimientoStock.getId() != null
                ? movimientoStock.getId().value()
                : null;

        return new MovimientoStockView(
                movimientoId,
                productoDto,
                tipoMovimientoStr,
                movimientoStock.getCantidad(),
                movimientoStock.getReferencia(),
                usuarioDto,
                movimientoStock.getFecha(),
                movimientoStock.getCreadoEn(),
                movimientoStock.getActualizadoEn()
        );
    }
}
