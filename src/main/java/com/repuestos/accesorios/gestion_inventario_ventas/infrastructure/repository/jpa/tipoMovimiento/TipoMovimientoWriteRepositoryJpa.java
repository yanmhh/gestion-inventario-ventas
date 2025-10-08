package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento.TipoMovimientoWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.tipoMovimiento.TipoMovimientoMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TipoMovimientoWriteRepositoryJpa implements TipoMovimientoWriteRepository {
    private final SpringDataTipoMovimientoRepository springDataTipoMovimientoRepository;
    private final TipoMovimientoMapperJpa tipoMovimientoMapperJpa;

    public TipoMovimientoWriteRepositoryJpa(SpringDataTipoMovimientoRepository springDataTipoMovimientoRepository,
                                            TipoMovimientoMapperJpa tipoMovimientoMapperJpa) {
        this.springDataTipoMovimientoRepository = springDataTipoMovimientoRepository;
        this.tipoMovimientoMapperJpa = tipoMovimientoMapperJpa;
    }

    @Override
    public TipoMovimiento save (TipoMovimiento tipoMovimiento){
        return tipoMovimientoMapperJpa.toDomain(springDataTipoMovimientoRepository.save(tipoMovimientoMapperJpa.toEntity(tipoMovimiento)));
    }

    @Override
    public void deleteById(Integer id){
        springDataTipoMovimientoRepository.deleteById(id);
    }

    @Override
    public Optional<TipoMovimiento> findById(Integer id){
        return springDataTipoMovimientoRepository.findById(id).map(tipoMovimientoMapperJpa::toDomain);
    }

    @Override
    public Optional<TipoMovimiento> findByNombre(String nombre){
        return springDataTipoMovimientoRepository.findByNombre(nombre).map(tipoMovimientoMapperJpa::toDomain);
    }

}
