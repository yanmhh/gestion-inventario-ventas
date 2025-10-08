package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.exception;

import java.io.IOException;

public class MessageProcessingException extends RuntimeException {
    public MessageProcessingException(String message) {
        super(message);
    }
}
