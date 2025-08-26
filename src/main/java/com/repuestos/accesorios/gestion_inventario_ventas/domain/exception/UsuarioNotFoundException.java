package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
}
