package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca;

public class MarcaView {
    private final Integer id;
    private final String nombre;

    public MarcaView(Integer id, String nombre) {
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
