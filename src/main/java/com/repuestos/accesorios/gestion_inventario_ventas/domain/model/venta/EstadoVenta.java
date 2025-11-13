package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.EstadoVentaDesconocidoException;


public enum EstadoVenta {
    PENDIENTE,
    CONFIRMADA,
    ANULADA;

    public static EstadoVenta fromString(String value) {
        try {
            return EstadoVenta.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new EstadoVentaDesconocidoException("Estado de Venta inválido: " + value);
        }
    }
}