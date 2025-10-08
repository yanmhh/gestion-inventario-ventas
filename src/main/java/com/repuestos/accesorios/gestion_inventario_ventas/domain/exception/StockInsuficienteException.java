package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
