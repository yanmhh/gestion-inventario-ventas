package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca;

import jakarta.validation.constraints.Size;

public class MarcaDto {


    @Size(max =100)
    private String nombre;

    public MarcaDto( String nombre){

        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }
}
