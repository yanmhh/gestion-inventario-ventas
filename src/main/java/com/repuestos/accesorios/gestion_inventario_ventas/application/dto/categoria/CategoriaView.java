package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria;

public class CategoriaView {
    private final Integer id;
    private final String nombre;

    public CategoriaView(Integer id, String nombre) {
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
