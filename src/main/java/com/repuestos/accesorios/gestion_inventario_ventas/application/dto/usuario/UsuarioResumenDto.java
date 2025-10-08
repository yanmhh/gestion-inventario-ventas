package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario;

public class UsuarioResumenDto {
    private Integer id;
    private String nombre;

    public UsuarioResumenDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
}

