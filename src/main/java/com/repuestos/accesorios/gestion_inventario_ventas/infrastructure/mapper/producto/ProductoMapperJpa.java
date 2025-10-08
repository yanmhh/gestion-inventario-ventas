package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.CodigoProducto;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.categoria.CategoriaMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.marca.MarcaMapperJpa;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapperJpa {

    private final MarcaMapperJpa marcaMapperJpa;
    private final CategoriaMapperJpa categoriaMapperJpa;

    public ProductoMapperJpa(MarcaMapperJpa marcaMapperJpa, CategoriaMapperJpa categoriaMapperJpa) {
        this.marcaMapperJpa = marcaMapperJpa;
        this.categoriaMapperJpa = categoriaMapperJpa;
    }

    public Producto toDomain(JpaProductoEntity jpaProductoEntity){
        if (jpaProductoEntity == null) {
            return null;
        }
        return new Producto(
                jpaProductoEntity.getId(),
                jpaProductoEntity.getNombre(),
                jpaProductoEntity.getDescripcion(),
                jpaProductoEntity.getPrecioVenta(),
                jpaProductoEntity.getCostoCompra(),
                jpaProductoEntity.getStock(),
                jpaProductoEntity.getStockMinimo(),
                new CodigoProducto(
                        jpaProductoEntity.getCodigo()),
                jpaProductoEntity.getImagenUrl(),
                new Marca(
                        jpaProductoEntity.getMarca().getId(),
                        jpaProductoEntity.getMarca().getNombre()),
                new Categoria(
                        jpaProductoEntity.getCategoria().getId(),
                        jpaProductoEntity.getCategoria().getNombre()),
                jpaProductoEntity.getCreadoEn()
        );
    }

    public JpaProductoEntity toEntity(Producto producto){
        if (producto == null) {
            return null;
        }
        JpaProductoEntity jpaProductoEntity = new JpaProductoEntity();
        jpaProductoEntity.setId(producto.getId());
        jpaProductoEntity.setNombre(producto.getNombre());
        jpaProductoEntity.setDescripcion(producto.getDescripcion());
        jpaProductoEntity.setPrecioVenta(producto.getPrecioVenta());
        jpaProductoEntity.setCostoCompra(producto.getCostoCompra());
        jpaProductoEntity.setStock(producto.getStock());
        jpaProductoEntity.setStockMinimo(producto.getStockMinimo());
        jpaProductoEntity.setCodigo(producto.getCodigo().codigo());
        jpaProductoEntity.setImagenUrl(producto.getImagenUrl());

        jpaProductoEntity.setMarca(marcaMapperJpa.toEntity(producto.getMarca()));
        jpaProductoEntity.setCategoria(categoriaMapperJpa.toEntity(producto.getCategoria()));

        jpaProductoEntity.setCreadoEn(producto.getCreadoEn());
        jpaProductoEntity.setActualizadoEn(producto.getActualizadoEn());

        return jpaProductoEntity;

    }
}
