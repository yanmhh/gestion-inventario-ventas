package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class ProductoNoEncontradoException extends RuntimeException{

    public ProductoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
