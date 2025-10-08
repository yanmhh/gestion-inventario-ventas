package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock.MovimientoStockWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.movimientoStock.MovimientoStockMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MovimientoStockWriteRepositoryJpaAdapter implements MovimientoStockWriteRepository {
    private final SpringDataMovimientoStockRepository springDataMovimientoStockRepository;
    private final MovimientoStockMapperJpa movimientoStockMapperJpa;

    public MovimientoStockWriteRepositoryJpaAdapter(SpringDataMovimientoStockRepository springDataMovimientoStockRepository, MovimientoStockMapperJpa movimientoStockMapperJpa) {
        this.springDataMovimientoStockRepository = springDataMovimientoStockRepository;
        this.movimientoStockMapperJpa = movimientoStockMapperJpa;
    }

    @Override
    public MovimientoStock save ( MovimientoStock movimientoStock){
        return movimientoStockMapperJpa.toDomain(springDataMovimientoStockRepository.save(movimientoStockMapperJpa.toEntity(movimientoStock)));
    }

    @Override
    public void deleteById(Integer id){
        springDataMovimientoStockRepository.deleteById(id);
    }

    @Override
    public Optional<MovimientoStock> findById(Integer id){
        return springDataMovimientoStockRepository.findById(id).map(movimientoStockMapperJpa::toDomain);
    }
}
