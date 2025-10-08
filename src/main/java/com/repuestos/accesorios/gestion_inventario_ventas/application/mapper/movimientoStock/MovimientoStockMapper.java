package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

public class MovimientoStockMapper {
    private MovimientoStockMapper(){
    }

    public static MovimientoStock from(RegistroMovimientoDto registerMovimientoStrockDto, Producto producto, TipoMovimiento tipoMovimiento, Usuario usuario){
        return new MovimientoStock(
                null,
                producto,
                tipoMovimiento,
                registerMovimientoStrockDto.getCantidad(),
                registerMovimientoStrockDto.getFecha(),
                registerMovimientoStrockDto.getReferencia(),
                usuario,
                LocalDateTime.now(),
                LocalDateTime.now()

        );
    }
}
