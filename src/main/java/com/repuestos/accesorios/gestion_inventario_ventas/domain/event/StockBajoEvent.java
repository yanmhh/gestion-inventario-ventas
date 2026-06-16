package com.repuestos.accesorios.gestion_inventario_ventas.domain.event;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;

import java.time.LocalDateTime;

public class StockBajoEvent {
    private final Producto producto;
    private final int stockActual;
    private final int stockMinimo;
    private final LocalDateTime ocurridoEn;

    public StockBajoEvent(Producto producto, int stockActual, int stockMinimo) {
        this.producto = producto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.ocurridoEn = LocalDateTime.now();
    }

    public Producto getProducto() {
        return producto;
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

    public String getMensaje() {
        return String.format("ALERTA: El producto '%s' (Código: %s) tiene stock bajo. Stock actual: %d, Stock mínimo: %d",
                producto.getNombre(),
                producto.getCodigo().codigo(),
                stockActual,
                stockMinimo);
    }
}
