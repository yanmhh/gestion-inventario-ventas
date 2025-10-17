package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca;

public class Marca {

    private  final Integer id;
    private String nombre;

    public Marca (Integer id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public void actualizarMarca(String nombre){
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
