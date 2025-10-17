package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria;

public class Categoria {

    private  final Integer id;
    private String nombre;

    public Categoria(Integer id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public void actualizarCategoria(String nombre){
        this.nombre =nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
