package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class StockInvalidoException extends RuntimeException {
    public StockInvalidoException(String message) {
        super(message);
    }
}
