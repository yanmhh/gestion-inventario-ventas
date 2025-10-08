package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MarcaDto {

    @NotNull
    private final Integer id;

    @Size(max =100)
    private final String nombre;

    public MarcaDto( Integer id, String nombre){
        this.id = id ;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
