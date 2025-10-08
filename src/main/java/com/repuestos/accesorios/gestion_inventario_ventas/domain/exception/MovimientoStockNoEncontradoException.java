package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class MovimientoStockNoEncontradoException extends RuntimeException {
    public MovimientoStockNoEncontradoException(String message) {
        super(message);
    }
}
