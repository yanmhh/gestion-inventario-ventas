package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Producto {
    private final Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private String codigo;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Producto(Integer id, String nombre, String descripcion,
                    BigDecimal precio, int stock,String codigo,
                    LocalDateTime creadoEn, LocalDateTime actualizadoEn){
        this.id = id;
        this.nombre = nombre;
        this.descripcion =descripcion;
        this.precio = precio;
        this.stock = stock;
        this.codigo = codigo;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public int getStock() {
        return this.stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getCreadoEn() {
        return this.creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return this.actualizadoEn;
    }
}
