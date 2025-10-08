package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;

import java.math.BigDecimal;

public class DetalleVenta {
    private final Integer id;
    private venta venta;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subTotal;

    public DetalleVenta(Integer id, venta venta, Producto producto, int cantidad, BigDecimal precioUnitario, BigDecimal subTotal) {
        this.id = id;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }

    public Integer getId() {
        return id;
    }

    public venta getVenta() {
        return venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
}
