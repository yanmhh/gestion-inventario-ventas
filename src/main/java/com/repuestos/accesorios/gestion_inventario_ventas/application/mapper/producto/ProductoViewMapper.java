package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoView;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;

import java.util.Objects;

public class ProductoViewMapper {
    private ProductoViewMapper(){
    }

    public static ProductoView from(Producto producto) {
        Objects.requireNonNull(producto, "El producto no puede ser null.");

        Marca marca = producto.getMarca();
        Categoria categoria = producto.getCategoria();
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
                marca != null ? new MarcaDto(marca.getId(), marca.getNombre()) : null,
                categoria != null ? new CategoriaDto(categoria.getId(), categoria.getNombre()) : null,
                producto.getCreadoEn(),
                producto.getActualizadoEn(),
                producto.stockBajo()
        );
    }

}
