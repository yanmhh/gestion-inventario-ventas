package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class EstadoVentaDesconocidoException extends RuntimeException {
    public EstadoVentaDesconocidoException(String message) {
        super(message);
    }
}
