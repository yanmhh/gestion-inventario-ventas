package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.inventario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

public class MovimientoMapper {
    private MovimientoMapper(){
    }

    public static MovimientoStock from (RegistroMovimientoDto registroMovimientoDto, Producto producto, TipoMovimiento tipoMovimiento, Usuario usuario){
        return new MovimientoStock(
                null,
                producto,
                tipoMovimiento,
                registroMovimientoDto.getCantidad(),
                registroMovimientoDto.getFecha(),
                registroMovimientoDto.getReferencia(),
                usuario,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

    }
}
