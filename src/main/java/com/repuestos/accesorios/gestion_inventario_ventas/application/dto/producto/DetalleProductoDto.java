package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
public class DetalleProductoDto {
    private final Integer id;
    private final String nombre;
    private final String codigo;
    private final BigDecimal precioVenta;
    private final String marca;
    private final String categoria;
}
