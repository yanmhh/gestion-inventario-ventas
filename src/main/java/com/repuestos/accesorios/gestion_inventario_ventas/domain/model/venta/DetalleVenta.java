package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.VentaInvalidaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import java.math.BigDecimal;

public class DetalleVenta {
    private final Integer id;
    private final Producto producto;
    private final int cantidad;
    private final BigDecimal precioUnitario;
    private Venta venta;

    public DetalleVenta(Integer id, Producto producto, int cantidad, BigDecimal precioUnitario) {
        validarProducto(producto);
        validarCantidad(cantidad);
        validarPrecio(precioUnitario);

        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    void asociarVenta(Venta venta) {
        if (venta == null) {
            throw new VentaInvalidaException("La venta asociada no puede ser nula.");
        }
        this.venta = venta;
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new VentaInvalidaException("El producto es obligatorio en el detalle de venta.");
        }
    }

    private void validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new VentaInvalidaException("La cantidad debe ser mayor que cero.");
        }
    }

    private void validarPrecio(BigDecimal precioUnitario) {
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new VentaInvalidaException("El precio unitario debe ser mayor que cero.");
        }
    }

    public Integer getId() {
        return id;
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
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    public Venta getVenta() {
        return venta;
    }
}
