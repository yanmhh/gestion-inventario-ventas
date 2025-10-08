package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.TipoMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoResumenDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioResumenDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.util.Objects;

public final class MovimientoStockViewMapper {
    private MovimientoStockViewMapper() {
    }

    public static MovimientoStockView toView(MovimientoStock movimientoStock) {
        Objects.requireNonNull(movimientoStock, "El movimiento de stock no puede ser null.");

        Producto producto = movimientoStock.getProducto();
        ProductoResumenDto productoDto = (producto != null)
                ? new ProductoResumenDto(
                producto.getId() != null ? producto.getId() : null,
                producto.getNombre())
                : null;

        Usuario usuario = movimientoStock.getUsuario();
        UsuarioResumenDto usuarioDto = (usuario != null)
                ? new UsuarioResumenDto(
                usuario.getId() != null ? usuario.getId() : null,
                usuario.getPersona().getNombre())
                : null;

        TipoMovimiento tipoMovimiento = movimientoStock.getTipoMovimiento();
        TipoMovimientoDto tipoMovimientoDto = (tipoMovimiento != null)
                ? new TipoMovimientoDto(
                tipoMovimiento.getId() != null ? tipoMovimiento.getId().value() : null,
                tipoMovimiento.getNombre(),
                tipoMovimiento.getDescripcion(),
                tipoMovimiento.getCreadoEn(),
                tipoMovimiento.getActualizadoEn()
        )
                : null;

        Integer movimientoId = movimientoStock.getId() != null ? movimientoStock.getId().value() : null;

        return new MovimientoStockView(
                movimientoId,
                productoDto,
                tipoMovimientoDto,
                movimientoStock.getCantidad(),
                movimientoStock.getReferencia(),
                usuarioDto,
                movimientoStock.getFecha(),
                movimientoStock.getCreadoEn(),
                movimientoStock.getActualizadoEn()
        );
    }
}
