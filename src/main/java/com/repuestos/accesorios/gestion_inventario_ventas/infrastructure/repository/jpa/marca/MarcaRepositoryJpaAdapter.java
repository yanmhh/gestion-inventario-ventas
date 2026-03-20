package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.marca.JpaMarcaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.marca.MarcaMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MarcaRepositoryJpaAdapter extends BaseRepositoryAdapter<Marca, JpaMarcaEntity, Integer>
        implements MarcaRepository {

    private final MarcaMapperJpa marcaMapperJpa;

    public MarcaRepositoryJpaAdapter(SpringDataMarcaRepository springDataMarcaRepository,
                                     MarcaMapperJpa marcaMapperJpa) {
        super(springDataMarcaRepository);
        this.marcaMapperJpa = marcaMapperJpa;
    }

    @Override
    protected Marca toDomain(JpaMarcaEntity entity) {
        return marcaMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaMarcaEntity toEntity(Marca domain) {
        return marcaMapperJpa.toEntity(domain);
    }

    @Override
    public Optional<Marca> findByNombre(String nombre) {
        return ((SpringDataMarcaRepository) repository).findByNombre(nombre)
                .map(this::toDomain);
    }

    @Override
    public List<Marca> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Marca save(Marca marca) {
        JpaMarcaEntity entity = toEntity(marca);
        JpaMarcaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Marca marca) {
        JpaMarcaEntity entity = toEntity(marca);
        repository.delete(entity);
    }
}
