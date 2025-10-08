package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento.TipoMovimientoReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.tipoMovimiento.TipoMovimientoMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoMovimientoReadRepositoryJpaAdapter implements TipoMovimientoReadRepository {

    private final SpringDataTipoMovimientoRepository springDataTipoMovimientoRepository;
    private final TipoMovimientoMapperJpa tipoMovimientoMapperJpa;

    public TipoMovimientoReadRepositoryJpaAdapter(SpringDataTipoMovimientoRepository springDataTipoMovimientoRepository,
                                                  TipoMovimientoMapperJpa tipoMovimientoMapperJpa) {
        this.springDataTipoMovimientoRepository = springDataTipoMovimientoRepository;
        this.tipoMovimientoMapperJpa = tipoMovimientoMapperJpa;
    }

    @Override
    public Optional<TipoMovimiento> findById(Integer id){
        return springDataTipoMovimientoRepository.findById(id).map(tipoMovimientoMapperJpa::toDomain);
    }

    @Override
    public Optional<TipoMovimiento> findByNombre(String nombre){
        return springDataTipoMovimientoRepository.findByNombre(nombre).map(tipoMovimientoMapperJpa::toDomain);
    }

    @Override
    public List<TipoMovimiento> findAll(){
        return springDataTipoMovimientoRepository.findAll().stream().map(tipoMovimientoMapperJpa::toDomain).toList();
    }
}
