package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock.MovimientoStockReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.movimientoStock.MovimientoStockMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovimientoStockReadRepositoryJpaAdapter implements MovimientoStockReadRepository {
    private final SpringDataMovimientoStockRepository springDataMovimientoStockRepository;
    private final MovimientoStockMapperJpa movimientoStockMapperJpa;

    public MovimientoStockReadRepositoryJpaAdapter(SpringDataMovimientoStockRepository springDataMovimientoStockRepository,
                                                   MovimientoStockMapperJpa movimientoStockMapperJpa) {
        this.springDataMovimientoStockRepository = springDataMovimientoStockRepository;
        this.movimientoStockMapperJpa = movimientoStockMapperJpa;
    }

    @Override
    public Optional<MovimientoStock> findById(Integer id) {
        return springDataMovimientoStockRepository.findById(id)
                .map(movimientoStockMapperJpa::toDomain);
    }

    @Override
    public List<MovimientoStock> findAll() {
        return springDataMovimientoStockRepository.findAll()
                .stream()
                .map(movimientoStockMapperJpa::toDomain)
                .toList();
    }

    @Override
    public List<MovimientoStock> findByProductoId(Integer productoId){
        return springDataMovimientoStockRepository.findAllByProductoId(productoId).stream().
                map(movimientoStockMapperJpa::toDomain).toList();
    }

}
