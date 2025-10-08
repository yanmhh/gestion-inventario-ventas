package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto;

import java.math.BigDecimal;

public class ProductoFilter {

    private String nombre;
    private String codigo;
    private BigDecimal precioMin;
    private BigDecimal precioMax;
    private Integer stockMin;
    private Integer stockMax;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getPrecioMin() {
        return this.precioMin;
    }

    public void setPrecioMin(BigDecimal precioMin) {
        this.precioMin = precioMin;
    }

    public BigDecimal getPrecioMax() {
        return this.precioMax;
    }

    public void setPrecioMax(BigDecimal precioMax) {
        this.precioMax = precioMax;
    }

    public Integer getStockMin() {
        return this.stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Integer getStockMax() {
        return this.stockMax;
    }

    public void setStockMax(Integer stockMax) {
        this.stockMax = stockMax;
    }
}
