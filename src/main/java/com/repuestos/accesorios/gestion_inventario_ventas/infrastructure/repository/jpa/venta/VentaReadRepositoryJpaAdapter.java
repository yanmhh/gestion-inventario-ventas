package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.EstadoVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta.VentaReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.EstadoVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta.VentaMapperJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VentaReadRepositoryJpaAdapter implements VentaReadRepository {
    private final SpringDataVentaRepository springDataVentaRepository;
    private final VentaMapperJpa ventaMapperJpa;

    @Override
    public Optional<Venta> findById(Integer id) {
        return springDataVentaRepository.findById(id)
                .map(ventaMapperJpa::toDomain);
    }

    @Override
    public List<Venta> findAll() {
        return springDataVentaRepository.findAll().stream()
                .map(ventaMapperJpa::toDomain)
                .toList();
    }

    @Override
    public boolean  existePorId(Integer id) {
        return springDataVentaRepository.existsById(id);
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
}
