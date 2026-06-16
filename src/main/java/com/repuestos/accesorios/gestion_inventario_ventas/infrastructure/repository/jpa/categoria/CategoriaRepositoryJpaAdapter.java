package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.categoria.JpaCategoriaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.categoria.CategoriaMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepositoryJpaAdapter extends BaseRepositoryAdapter<Categoria, JpaCategoriaEntity, Integer>
        implements CategoriaRepository {

    private final CategoriaMapperJpa categoriaMapperJpa;

    public CategoriaRepositoryJpaAdapter(SpringDataCategoriaRepository springDataCategoriaRepository,
                                         CategoriaMapperJpa categoriaMapperJpa) {
        super(springDataCategoriaRepository);
        this.categoriaMapperJpa = categoriaMapperJpa;
    }

    @Override
    protected Categoria toDomain(JpaCategoriaEntity entity) {
        return categoriaMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaCategoriaEntity toEntity(Categoria domain) {
        return categoriaMapperJpa.toEntity(domain);
    }

    @Override
    public Optional<Categoria> findByNombre(String nombre) {
        return ((SpringDataCategoriaRepository) repository).findByNombre(nombre)
                .map(this::toDomain);
    }

    @Override
    public List<Categoria> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Categoria save(Categoria categoria) {
        JpaCategoriaEntity entity = toEntity(categoria);
        JpaCategoriaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Categoria categoria) {
        JpaCategoriaEntity entity = toEntity(categoria);
        repository.delete(entity);
    }
}
