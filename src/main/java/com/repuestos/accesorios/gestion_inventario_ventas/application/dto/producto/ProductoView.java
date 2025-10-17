package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaView;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoView {

    private final Integer id;
    private final String nombre;
    private final String descripcion;

    @JsonProperty("precio_venta")
    private final BigDecimal precioVenta;

    @JsonProperty("costo_compra")
    private final BigDecimal costoCompra;
    private final int stock;

    @JsonProperty("stock_minimo")
    private final int stockMinimo;
    private final String codigo;

    @JsonProperty("imagen_url")
    private final String imagenUrl;

    private final MarcaView marca;
    private final CategoriaView categoria;

    @JsonProperty("creado_en")
    private final LocalDateTime creadoEn;

    @JsonProperty("actualizado_en")
    private final LocalDateTime actualizadoEn;

    private final boolean stockBajo;

    public ProductoView(Integer id, String nombre, String descripcion, BigDecimal precioVenta,BigDecimal costoCompra, int stock
            ,int stockMinimo, String codigo, String imagenUrl,MarcaView marca, CategoriaView categoria, LocalDateTime creadoEn,
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

    public MarcaView getMarca() {
        return this.marca;
    }

    public CategoriaView getCategoria() {
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
