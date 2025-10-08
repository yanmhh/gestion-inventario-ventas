package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoView {

    private final Integer id;
    private final String nombre;
    private final String descripcion;
    private final BigDecimal precioVenta;
    private final BigDecimal costoCompra;
    private final int stock;
    private final int stockMinimo;
    private final String codigo;
    private final String imagenUrl;
    private final MarcaDto marca;
    private final CategoriaDto categoria;
    private final LocalDateTime creadoEn;
    private final LocalDateTime actualizadoEn;
    private final boolean stockBajo;

    public ProductoView(Integer id, String nombre, String descripcion, BigDecimal precioVenta,BigDecimal costoCompra, int stock
            ,int stockMinimo, String codigo, String imagenUrl,MarcaDto marca, CategoriaDto categoria, LocalDateTime creadoEn,
                        LocalDateTime actualizadoEn, boolean stockBajo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.costoCompra = costoCompra;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.codigo = codigo;
        this.imagenUrl = imagenUrl;
        this.marca = marca;
        this.categoria = categoria;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
        this.stockBajo = stockBajo;
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

    public BigDecimal getPrecioVenta() {
        return this.precioVenta;
    }

    public BigDecimal getCostoCompra() {
        return this.costoCompra;
    }

    public int getStock() {
        return this.stock;
    }

    public int getStockMinimo() {
        return this.stockMinimo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getImagenUrl(){
        return this.imagenUrl;
    }

    public MarcaDto getMarca() {
        return this.marca;
    }

    public CategoriaDto getCategoria() {
        return this.categoria;
    }

    public LocalDateTime getCreadoEn() {
        return this.creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return this.actualizadoEn;
    }

    public boolean isStockBajo() {
        return this.stockBajo;
    }
}
