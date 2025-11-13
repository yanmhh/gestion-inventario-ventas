package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Objects;

public final class MovimientoStockMapper {
    private MovimientoStockMapper(){
    }

    public static MovimientoStock from(RegistroMovimientoDto registroMovimiento, Producto producto, Usuario usuario){

        Objects.requireNonNull(registroMovimiento, "registroMovimiento no puede ser null");
        Objects.requireNonNull(producto, "producto no puede ser null");
        Objects.requireNonNull(usuario, "usuario no puede ser null");

        TipoMovimiento tipoMovimiento = TipoMovimiento.fromString(registroMovimiento.getTipoMovimiento());

        return new MovimientoStock(
                null,
                producto,
                tipoMovimiento,
                registroMovimiento.getCantidad(),
                registroMovimiento.getFecha(),
                registroMovimiento.getReferencia(),
                usuario,
                LocalDateTime.now(),
                LocalDateTime.now()

        );
    }
}
