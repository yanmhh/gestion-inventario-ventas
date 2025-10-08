package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto;

public class ProductoResumenDto {
    private final Integer id;
    private final String nombre;

    public ProductoResumenDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
}

