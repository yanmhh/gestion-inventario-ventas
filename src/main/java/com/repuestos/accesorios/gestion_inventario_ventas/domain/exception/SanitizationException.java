package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class SanitizationException extends RuntimeException {
    public SanitizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
