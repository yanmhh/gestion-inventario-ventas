package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class ProductoNotFoundException extends RuntimeException{

    public ProductoNotFoundException(String mensaje){
        super(mensaje);
    }
}
