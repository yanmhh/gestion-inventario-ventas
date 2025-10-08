package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cotizacion;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ValorInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.Dinero;

public class DetalleCotizacion {

    private final Integer id;
    private final Producto producto;
    private final int cantidad;
    private final Dinero precioUnitario;

    public DetalleCotizacion(Integer id, Producto producto, int cantidad, Dinero precioUnitario) {

        validarCantidad(cantidad);
        validarProducto(producto);
        validarPrecio(precioUnitario);

        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    private void validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new ValorInvalidoException("La cantidad debe ser mayor a cero");
        }
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new ValorInvalidoException("El producto no puede ser nulo");
        }
    }

    private void validarPrecio(Dinero precio) {
        if (precio == null) {
            throw new ValorInvalidoException("El precio unitario es obligatorio");
        }
    }

    public Dinero subtotal() {
        return precioUnitario.multiplicar(cantidad);
    }
    public Integer getId() { return id; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public Dinero getPrecioUnitario() { return precioUnitario; }
}

