package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapperJpa {

    public Producto toDomain(JpaProductoEntity jpaProductoEntity){
        return new Producto(
                jpaProductoEntity.getId(),
                jpaProductoEntity.getNombre(),
                jpaProductoEntity.getDescripcion(),
                jpaProductoEntity.getPrecio(),
                jpaProductoEntity.getStock(),
                jpaProductoEntity.getCodigo(),
                jpaProductoEntity.getCreadoEn(),
                jpaProductoEntity.getActualizadoEn()
        );
    }

    public JpaProductoEntity toEntity(Producto producto){
        JpaProductoEntity jpaProductoEntity = new JpaProductoEntity();
        jpaProductoEntity.setId(producto.getId());
        jpaProductoEntity.setNombre(producto.getNombre());
        jpaProductoEntity.setDescripcion(producto.getDescripcion());
        jpaProductoEntity.setPrecio(producto.getPrecio());
        jpaProductoEntity.setStock(producto.getStock());
        jpaProductoEntity.setCodigo(producto.getCodigo());
        jpaProductoEntity.setCreadoEn(producto.getCreadoEn());
        jpaProductoEntity.setActualizadoEn(producto.getActualizadoEn());

        return jpaProductoEntity;

    }
}
