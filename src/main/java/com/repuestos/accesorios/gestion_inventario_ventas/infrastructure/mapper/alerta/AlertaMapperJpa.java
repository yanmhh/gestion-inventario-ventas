package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.alerta.Alerta;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.alerta.JpaAlertaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.producto.ProductoMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.usuario.UsuarioMapperJpa;
import org.springframework.stereotype.Component;

@Component
public class AlertaMapperJpa {

    private final ProductoMapperJpa productoMapperJpa;
    private final UsuarioMapperJpa usuarioMapperJpa;

    public AlertaMapperJpa(ProductoMapperJpa productoMapperJpa, UsuarioMapperJpa usuarioMapperJpa) {
        this.productoMapperJpa = productoMapperJpa;
        this.usuarioMapperJpa = usuarioMapperJpa;
    }

    public Alerta toDomain(JpaAlertaEntity entity) {
        return new Alerta(
                entity.getId(),
                productoMapperJpa.toDomain(entity.getProducto()),
                usuarioMapperJpa.toDomain(entity.getUsuario()),
                entity.getMensaje(),
                entity.isLeido(),
                entity.getCreadoEn()
        );
    }

    public JpaAlertaEntity toEntity(Alerta alerta) {
        JpaAlertaEntity entity = new JpaAlertaEntity();
        entity.setId(alerta.getId());
        entity.setProducto(productoMapperJpa.toEntity(alerta.getProducto()));
        entity.setUsuario(usuarioMapperJpa.toEntity(alerta.getUsuario()));
        entity.setMensaje(alerta.getMensaje());
        entity.setLeido(alerta.isLeido());
        entity.setCreadoEn(alerta.getCreadoEn());
        return entity;
    }
}
