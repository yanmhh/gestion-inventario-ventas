package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria;

import jakarta.validation.constraints.Size;

public class CategoriaDto {

    @Size(max =100)
    private String nombre;

    public CategoriaDto(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
