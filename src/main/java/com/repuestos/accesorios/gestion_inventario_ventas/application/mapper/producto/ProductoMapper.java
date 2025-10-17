package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.RegisterProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.UpdateProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.CodigoProducto;

import java.time.LocalDateTime;

public final class ProductoMapper {

    private ProductoMapper(){
    }

    public static Producto from(RegisterProductoCommand registerRequest, Marca marca, Categoria categoria){
        if (registerRequest == null) return null;


        return new Producto(
                null,
                registerRequest.getNombre(),
                registerRequest.getDescripcion(),
                registerRequest.getPrecioVenta(),
                registerRequest.getCostoCompra(),
                registerRequest.getStock(),
                registerRequest.getStockMinimo(),
                new CodigoProducto(registerRequest.getCodigo()),
                registerRequest.getImagenUrl(),
                marca,
                categoria,
                LocalDateTime.now()
        );
    }

    public static  void mapUpdateData(UpdateProductoCommand updateRequest, Producto producto, Marca marca, Categoria categoria){
        producto.actualizarProducto(
        updateRequest.getNombre(),
        updateRequest.getDescripcion(),
        updateRequest.getPrecioVenta(),
        updateRequest.getCostoCompra(),
        updateRequest.getStock(),
        updateRequest.getStockMinimo(),
        new CodigoProducto(updateRequest.getCodigo()),
        updateRequest.getImagenUrl(),
        marca,
        categoria,
        LocalDateTime.now()
        );
    }
}
