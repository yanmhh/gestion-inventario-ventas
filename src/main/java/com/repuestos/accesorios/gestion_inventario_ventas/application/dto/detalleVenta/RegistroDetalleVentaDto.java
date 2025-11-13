package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class RegistroDetalleVentaDto {

    private Integer productoId;
    private int cantidad;
    private BigDecimal precioUnitario;
}
