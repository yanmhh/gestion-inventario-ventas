package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta.DetalleVentaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.detalleVenta.JpaDetalleVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta.DetalleVentaMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DetalleVentaRepositoryJpaAdapter extends BaseRepositoryAdapter<DetalleVenta, JpaDetalleVentaEntity, Integer>
        implements DetalleVentaRepository {

    private final DetalleVentaMapperJpa detalleVentaMapperJpa;

    public DetalleVentaRepositoryJpaAdapter(SpringDataDetalleVentaRepository springDataDetalleVentaRepository,
                                            DetalleVentaMapperJpa detalleVentaMapperJpa) {
        super(springDataDetalleVentaRepository);
        this.detalleVentaMapperJpa = detalleVentaMapperJpa;
    }

    @Override
    protected DetalleVenta toDomain(JpaDetalleVentaEntity entity) {
        return detalleVentaMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaDetalleVentaEntity toEntity(DetalleVenta domain) {
        return detalleVentaMapperJpa.toEntity(domain);
    }

    @Override
    public List<DetalleVenta> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleVenta> findByVentaId(Integer ventaId) {
        return ((SpringDataDetalleVentaRepository) repository).findByVentaId(ventaId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public DetalleVenta save(DetalleVenta detalle) {
        JpaDetalleVentaEntity entity = toEntity(detalle);
        JpaDetalleVentaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public DetalleVenta update(DetalleVenta detalle) {
        JpaDetalleVentaEntity entity = toEntity(detalle);
        JpaDetalleVentaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(DetalleVenta detalleVenta) {
        JpaDetalleVentaEntity entity = toEntity(detalleVenta);
        repository.delete(entity);
    }
}
