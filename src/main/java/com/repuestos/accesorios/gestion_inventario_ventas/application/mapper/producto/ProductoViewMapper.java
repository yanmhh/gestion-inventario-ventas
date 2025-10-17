package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoView;

import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria.CategoriaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca.MarcaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;

import java.util.Objects;

public final class ProductoViewMapper {
    private ProductoViewMapper(){
    }

    public static ProductoView toView(Producto producto) {
        Objects.requireNonNull(producto, "El producto no puede ser null.");

        return new ProductoView(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioVenta(),
                producto.getCostoCompra(),
                producto.getStock(),
                producto.getStockMinimo(),
                producto.getCodigo().codigo(),
                producto.getImagenUrl(),
                MarcaViewMapper.toView(producto.getMarca()),
                CategoriaViewMapper.toView(producto.getCategoria()),
                producto.getCreadoEn(),
                producto.getActualizadoEn(),
                producto.stockBajo()
        );
    }

}
