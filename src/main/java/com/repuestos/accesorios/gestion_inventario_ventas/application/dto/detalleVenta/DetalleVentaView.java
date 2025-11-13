package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.DetalleProductoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
public class DetalleVentaView {

    private final Integer id;
    private final DetalleProductoDto producto;
    private final int cantidad;
    private final BigDecimal precioUnitario;
}
