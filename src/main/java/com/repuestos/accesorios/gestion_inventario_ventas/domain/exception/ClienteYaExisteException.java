package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String message) {
        super(message);
    }
}
