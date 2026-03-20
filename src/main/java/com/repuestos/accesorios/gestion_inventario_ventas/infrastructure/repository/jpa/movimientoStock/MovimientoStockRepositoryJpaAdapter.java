package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock.MovimientoStockRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.movimientoStock.JpaMovimientoStockEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.movimientoStock.MovimientoStockMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovimientoStockRepositoryJpaAdapter extends BaseRepositoryAdapter<MovimientoStock, JpaMovimientoStockEntity, Integer>
        implements MovimientoStockRepository {

    private final MovimientoStockMapperJpa movimientoStockMapperJpa;

    public MovimientoStockRepositoryJpaAdapter(SpringDataMovimientoStockRepository springDataMovimientoStockRepository,
                                               MovimientoStockMapperJpa movimientoStockMapperJpa) {
        super(springDataMovimientoStockRepository);
        this.movimientoStockMapperJpa = movimientoStockMapperJpa;
    }

    @Override
    protected MovimientoStock toDomain(JpaMovimientoStockEntity entity) {
        return movimientoStockMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaMovimientoStockEntity toEntity(MovimientoStock domain) {
        return movimientoStockMapperJpa.toEntity(domain);
    }

    @Override
    public List<MovimientoStock> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<MovimientoStock> findByProductoId(Integer productoId) {
        return ((SpringDataMovimientoStockRepository) repository).findAllByProductoId(productoId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public MovimientoStock save(MovimientoStock movimientoStock) {
        JpaMovimientoStockEntity entity = toEntity(movimientoStock);
        JpaMovimientoStockEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
