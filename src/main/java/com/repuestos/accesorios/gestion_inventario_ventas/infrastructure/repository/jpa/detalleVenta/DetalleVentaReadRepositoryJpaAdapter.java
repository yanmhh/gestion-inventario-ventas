package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta.DetalleVentaReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta.DetalleVentaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DetalleVentaReadRepositoryJpaAdapter implements DetalleVentaReadRepository {
    private final SpringDataDetalleVentaRepository springDataDetalleVentaRepository;
    private final DetalleVentaMapperJpa detalleVentaMapperJpa;

    public DetalleVentaReadRepositoryJpaAdapter(SpringDataDetalleVentaRepository springDataDetalleVentaRepository, DetalleVentaMapperJpa detalleVentaMapperJpa) {
        this.springDataDetalleVentaRepository = springDataDetalleVentaRepository;
        this.detalleVentaMapperJpa = detalleVentaMapperJpa;
    }

    @Override
    public Optional<DetalleVenta> findById(Integer id){
        return springDataDetalleVentaRepository.findById(id).map(detalleVentaMapperJpa::toDomain);
    }

    @Override
    public List<DetalleVenta> findAll() {
        return springDataDetalleVentaRepository.findAll()
                .stream()
                .map(detalleVentaMapperJpa::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleVenta> findByVentaId(Integer ventaId) {
        return springDataDetalleVentaRepository.findByVentaId(ventaId)
                .stream()
                .map(detalleVentaMapperJpa::toDomain)
                .collect(Collectors.toList());
    }

}
