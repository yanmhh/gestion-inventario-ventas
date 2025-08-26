package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.RegisterProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;

import java.time.LocalDateTime;

public class ProductoMapper {

    public static Producto from(RegisterProductoCommand registerRequest){
        return new Producto(
                null,
                registerRequest.getNombre(),
                registerRequest.getDescripcion(),
                registerRequest.getPrecio(),
                registerRequest.getStock(),
                registerRequest.getCodigo(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

    }
}
