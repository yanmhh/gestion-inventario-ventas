package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
}
