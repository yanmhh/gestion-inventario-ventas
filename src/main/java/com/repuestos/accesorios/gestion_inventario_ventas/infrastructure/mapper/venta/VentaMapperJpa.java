package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.JpaVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.cliente.ClienteMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.usuario.UsuarioMapperJpa;
import org.springframework.stereotype.Component;

@Component
public class VentaMapperJpa {

    private final ClienteMapperJpa clienteMapperJpa;
    private final EstadoVentaMapperJpa estadoVentaMapperJpa;
    private final UsuarioMapperJpa usuarioMapperJpa;
    private final DetalleVentaMapperJpa detalleVentaMapperJpa;

    public VentaMapperJpa(
            ClienteMapperJpa clienteMapperJpa,
            EstadoVentaMapperJpa estadoVentaMapperJpa,
            UsuarioMapperJpa usuarioMapperJpa,
            DetalleVentaMapperJpa detalleVentaMapperJpa
    ) {
        this.clienteMapperJpa = clienteMapperJpa;
        this.estadoVentaMapperJpa = estadoVentaMapperJpa;
        this.usuarioMapperJpa = usuarioMapperJpa;
        this.detalleVentaMapperJpa = detalleVentaMapperJpa;
    }

    // Convierte de entidad JPA a dominio
    public Venta toDomain(JpaVentaEntity entity) {
        if (entity == null) return null;

        Venta venta = new Venta(
                entity.getId(),
                clienteMapperJpa.toDomain(entity.getCliente()),
                entity.getTipoDocumento(),
                estadoVentaMapperJpa.toDomain(entity.getEstado()),
                usuarioMapperJpa.toDomain(entity.getUsuario()),
                entity.getObservaciones(),
                entity.getCreadoEn()
        );

        entity.getDetalles()
                .stream()
                .map(detalleVentaMapperJpa::toDomain)
                .forEach(venta::agregarDetalle);

        return venta;
    }

    // Convierte de dominio a entidad JPA
    public JpaVentaEntity toEntity(Venta venta) {
        if (venta == null) return null;

        JpaVentaEntity entity = new JpaVentaEntity();
        entity.setId(venta.getId());
        entity.setTipoDocumento(venta.getTipoDocumento());
        entity.setEstado(estadoVentaMapperJpa.toEntity(venta.getEstado()));
        entity.setObservaciones(venta.getObservaciones());
        entity.setCreadoEn(venta.getCreadoEn());
        entity.setFechaVenta(venta.getFechaVenta());
        entity.setTotal(venta.getTotal());

        // Mapear Cliente completo usando ClienteMapperJpa
        if (venta.getCliente() != null) {
            entity.setCliente(clienteMapperJpa.toEntity(venta.getCliente()));
        }

        // Mapear Usuario completo usando UsuarioMapperJpa
        if (venta.getUsuario() != null) {
            entity.setUsuario(usuarioMapperJpa.toEntity(venta.getUsuario()));
        }

        // Mapear detalles correctamente
        if (venta.getDetalles() != null && !venta.getDetalles().isEmpty()) {
            venta.getDetalles().stream()
                    .map(detalleVentaMapperJpa::toEntity)
                    .forEach(detalleEntity -> {
                        detalleEntity.setVenta(entity);
                        entity.agregarDetalle(detalleEntity);
                    });
        }

        return entity;
    }
}
