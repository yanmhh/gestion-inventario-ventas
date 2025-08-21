package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }
}
