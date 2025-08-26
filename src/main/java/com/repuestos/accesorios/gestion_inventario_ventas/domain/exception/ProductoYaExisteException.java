package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class ProductoYaExisteException extends RuntimeException{
    public ProductoYaExisteException(String mensaje){
        super(mensaje);
    }
}
