package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoriaDto {

    @NotNull
    private Integer id;

    @Size(max =100)
    private String nombre;

    public CategoriaDto(Integer id, String nombre){
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
