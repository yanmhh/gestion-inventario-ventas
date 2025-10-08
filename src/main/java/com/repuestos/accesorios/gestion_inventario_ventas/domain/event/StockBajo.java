package com.repuestos.accesorios.gestion_inventario_ventas.domain.event;

import java.time.LocalDateTime;

public class StockBajo {
    private final Integer productoId;
    private final String nombreProducto;
    private final int stockActual;
    private final int stockMinimo;
    private final LocalDateTime ocurridoEn;

    public StockBajo(Integer productoId, String nombreProducto, int stockActual, int stockMinimo) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.ocurridoEn = LocalDateTime.now();
    }

    public Integer getProductoId() {
        return productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public LocalDateTime getOcurridoEn() {
        return ocurridoEn;
    }
}
