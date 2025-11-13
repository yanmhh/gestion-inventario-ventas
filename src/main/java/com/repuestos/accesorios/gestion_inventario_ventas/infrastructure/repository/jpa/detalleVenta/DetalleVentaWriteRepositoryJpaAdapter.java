package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta.DetalleVentaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta.DetalleVentaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class DetalleVentaWriteRepositoryJpaAdapter implements DetalleVentaWriteRepository {
    private final SpringDataDetalleVentaRepository springDataDetalleVentaRepository;
    private final DetalleVentaMapperJpa detalleVentaMapperJpa;

    public DetalleVentaWriteRepositoryJpaAdapter(SpringDataDetalleVentaRepository springDataDetalleVentaRepository, DetalleVentaMapperJpa detalleVentaMapperJpa) {
        this.springDataDetalleVentaRepository = springDataDetalleVentaRepository;
        this.detalleVentaMapperJpa = detalleVentaMapperJpa;
    }

    @Override
    public Optional<DetalleVenta> findById(Integer id) {
        return springDataDetalleVentaRepository.findById(id)
                .map(detalleVentaMapperJpa::toDomain);
    }

    @Override
    public DetalleVenta save (DetalleVenta detalleVenta) {
        return detalleVentaMapperJpa.toDomain(springDataDetalleVentaRepository.save(detalleVentaMapperJpa.toEntity(detalleVenta)));
    }

    @Override
    public DetalleVenta update (DetalleVenta detalleVenta) {
        return detalleVentaMapperJpa.toDomain(springDataDetalleVentaRepository.save(detalleVentaMapperJpa.toEntity(detalleVenta)));
    }


    @Override
    public void delete(DetalleVenta detalleVenta) {
        springDataDetalleVentaRepository.delete(detalleVentaMapperJpa.toEntity(detalleVenta));
    }
}


