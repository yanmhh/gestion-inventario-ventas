package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

public final class MovimientoStockMapper {
    private MovimientoStockMapper(){
    }

    public static MovimientoStock from(RegistroMovimientoDto registroMovimiento, Producto producto, Usuario usuario){

        if (registroMovimiento == null || producto == null || usuario == null) {
            throw new IllegalArgumentException("Los parámetros registroMovimiento, producto y usuario no pueden ser null");
        }

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
