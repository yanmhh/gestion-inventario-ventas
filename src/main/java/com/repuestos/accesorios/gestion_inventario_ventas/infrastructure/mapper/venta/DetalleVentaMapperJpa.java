package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.detalleVenta.JpaDetalleVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.producto.ProductoMapperJpa;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapperJpa {
    private final ProductoMapperJpa productoMapperJpa;

    public DetalleVentaMapperJpa(ProductoMapperJpa productoMapperJpa) {
        this.productoMapperJpa = productoMapperJpa;
    }

    public DetalleVenta toDomain(JpaDetalleVentaEntity entity) {
        if (entity == null) return null;
        return new DetalleVenta(
                entity.getId(),
                productoMapperJpa.toDomain(entity.getProducto()),
                entity.getCantidad(),
                entity.getPrecioUnitario()
        );
    }

    public JpaDetalleVentaEntity toEntity(DetalleVenta detalle) {
        if (detalle == null) return null;
        JpaDetalleVentaEntity entity = new JpaDetalleVentaEntity();
        entity.setId(detalle.getId());
        entity.setProducto(productoMapperJpa.toEntity(detalle.getProducto()));
        entity.setCantidad(detalle.getCantidad());
        entity.setPrecioUnitario(detalle.getPrecioUnitario());
        return entity;
    }
}
