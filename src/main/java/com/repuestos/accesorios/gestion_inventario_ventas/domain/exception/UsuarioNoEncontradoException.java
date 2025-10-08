package com.repuestos.accesorios.gestion_inventario_ventas.domain.exception;

public class UsuarioNoEncontradoException extends RuntimeException{

    public UsuarioNoEncontradoException() {
        super("Usuario no encontrado");
    }

    public UsuarioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
