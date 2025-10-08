package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception;

public class WebSocketDomainException extends RuntimeException{
    public WebSocketDomainException(String message) {
        super(message);
    }
    public WebSocketDomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
