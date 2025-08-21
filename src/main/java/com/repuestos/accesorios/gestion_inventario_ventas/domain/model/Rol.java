package com.repuestos.accesorios.gestion_inventario_ventas.domain.model;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.JpaRolEntity;

public class Rol {
    private final Integer id;
    private String nombre;

    public Rol(Integer id, String nombre){
        this.id = id;
        this.nombre = nombre;

    }

    public Integer getId(){
        return this.id;
    }

    public  String getNombre(){
        return this.nombre;
    }
}
