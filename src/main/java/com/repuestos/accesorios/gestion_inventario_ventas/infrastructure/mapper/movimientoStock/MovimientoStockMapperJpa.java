package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.MovimientoStockId;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.movimientoStock.JpaMovimientoStockEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.producto.ProductoMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.tipoMovimiento.TipoMovimientoMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.usuario.UsuarioMapperJpa;
import org.springframework.stereotype.Component;

@Component
public class MovimientoStockMapperJpa {
    private final ProductoMapperJpa productoMapperJpa;
    private final TipoMovimientoMapperJpa tipoMovimientoMapperJpa;
    private final UsuarioMapperJpa usuarioMapperJpa;

    public MovimientoStockMapperJpa(
            ProductoMapperJpa productoMapperJpa,
            TipoMovimientoMapperJpa tipoMovimientoMapperJpa,
            UsuarioMapperJpa usuarioMapperJpa
    ) {
        this.productoMapperJpa = productoMapperJpa;
        this.tipoMovimientoMapperJpa = tipoMovimientoMapperJpa;
        this.usuarioMapperJpa = usuarioMapperJpa;
    }

    public MovimientoStock toDomain(JpaMovimientoStockEntity jpaMovimientoStockEntity) {
        if (jpaMovimientoStockEntity == null) return null;

        return new MovimientoStock(
                jpaMovimientoStockEntity.getId() != null ? new MovimientoStockId(jpaMovimientoStockEntity.getId()) : null,
                productoMapperJpa.toDomain(jpaMovimientoStockEntity.getProducto()),
                tipoMovimientoMapperJpa.toDomain(jpaMovimientoStockEntity.getTipoMovimiento()),
                jpaMovimientoStockEntity.getCantidad(),
                jpaMovimientoStockEntity.getFecha(),
                jpaMovimientoStockEntity.getReferencia(),
                usuarioMapperJpa.toDomain(jpaMovimientoStockEntity.getUsuario()),
                jpaMovimientoStockEntity.getCreadoEn(),
                jpaMovimientoStockEntity.getActualizadoEn()
        );
}

    public JpaMovimientoStockEntity toEntity(MovimientoStock MovimientoStock) {
        if (MovimientoStock == null) return null;

        JpaMovimientoStockEntity entity = new JpaMovimientoStockEntity();

        if (MovimientoStock.getId() != null) {
            entity.setId(MovimientoStock.getId().value());
        }

        entity.setProducto(productoMapperJpa.toEntity(MovimientoStock.getProducto()));
        entity.setTipoMovimiento(tipoMovimientoMapperJpa.toEntity(MovimientoStock.getTipoMovimiento()));
        entity.setCantidad(MovimientoStock.getCantidad());
        entity.setFecha(MovimientoStock.getFecha());
        entity.setReferencia(MovimientoStock.getReferencia());
        entity.setUsuario(usuarioMapperJpa.toEntity(MovimientoStock.getUsuario()));
        entity.setCreadoEn(MovimientoStock.getCreadoEn());
        entity.setActualizadoEn(MovimientoStock.getActualizadoEn());

        return entity;
    }

}
