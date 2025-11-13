package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.EstadoVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta.VentaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.EstadoVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta.VentaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VentaWriteRepositoryJpaAdapter implements VentaWriteRepository {
    private final SpringDataVentaRepository springDataVentaRepository;
    private final VentaMapperJpa ventaMapperJpa;

    public VentaWriteRepositoryJpaAdapter(SpringDataVentaRepository springDataVentaRepository, VentaMapperJpa ventaMapperJpa) {
        this.springDataVentaRepository = springDataVentaRepository;
        this.ventaMapperJpa = ventaMapperJpa;
    }

    @Override
    public Optional<Venta> findById(Integer id) {
        return springDataVentaRepository.findById(id)
                .map(ventaMapperJpa::toDomain);
    }

    @Override
    public List<Venta> findByEstado(EstadoVenta estado) {
        EstadoVentaEntity estadoEntity = EstadoVentaEntity.valueOf(estado.name());
        return springDataVentaRepository.findByEstado(estadoEntity).stream()
                .map(ventaMapperJpa::toDomain)
                .toList();
    }

    @Override
    public List<Venta> findByCliente(Integer clienteId) {
        return springDataVentaRepository.findByClienteId(clienteId).stream()
                .map(ventaMapperJpa::toDomain)
                .toList();
    }

    @Override
    public Venta save(Venta venta) {
        return ventaMapperJpa.toDomain(springDataVentaRepository.save(ventaMapperJpa.toEntity(venta)));
    }

    @Override
    public void delete(Integer id) {
        springDataVentaRepository.deleteById(id);
    }
}
