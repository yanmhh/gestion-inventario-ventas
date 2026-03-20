package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.EstadoVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta.VentaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.EstadoVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.JpaVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.venta.VentaMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VentaRepositoryJpaAdapter extends BaseRepositoryAdapter<Venta, JpaVentaEntity, Integer>
        implements VentaRepository {

    private final VentaMapperJpa ventaMapperJpa;

    public VentaRepositoryJpaAdapter(SpringDataVentaRepository springDataVentaRepository,
                                     VentaMapperJpa ventaMapperJpa) {
        super(springDataVentaRepository);
        this.ventaMapperJpa = ventaMapperJpa;
    }

    @Override
    protected Venta toDomain(JpaVentaEntity entity) {
        return ventaMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaVentaEntity toEntity(Venta domain) {
        return ventaMapperJpa.toEntity(domain);
    }

    // ==================== Métodos de Búsqueda ====================
    // findById() heredado de BaseRepositoryAdapter

    // ==================== Métodos de Lectura ====================

    @Override
    public List<Venta> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public boolean existePorId(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public List<Venta> findByEstado(EstadoVenta estado) {
        EstadoVentaEntity estadoEntity = EstadoVentaEntity.valueOf(estado.name());
        return ((SpringDataVentaRepository) repository).findByEstado(estadoEntity).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Venta> findByCliente(Integer clienteId) {
        return ((SpringDataVentaRepository) repository).findByClienteId(clienteId).stream()
                .map(this::toDomain)
                .toList();
    }

    // ==================== Métodos de Escritura ====================

    @Override
    public Venta save(Venta venta) {
        JpaVentaEntity entity = toEntity(venta);
        JpaVentaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
