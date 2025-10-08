package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.categoria.CategoriaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoriaWriteRepositoryJpaAdapter implements CategoriaWriteRepository {

    private final SpringDataCategoriaRepository springDataCategoriaRepository;
    private final CategoriaMapperJpa categoriaMapperJpa;

    public CategoriaWriteRepositoryJpaAdapter(SpringDataCategoriaRepository springDataCategoriaRepository, CategoriaMapperJpa categoriaMapperJpa){
        this.springDataCategoriaRepository = springDataCategoriaRepository;
        this.categoriaMapperJpa = categoriaMapperJpa;
    }

    @Override
    public Categoria save ( Categoria categoria){
        return categoriaMapperJpa.toDomain(springDataCategoriaRepository.save(categoriaMapperJpa.toEntity(categoria)));
    }

    @Override
    public void delete (Categoria categoria){
        springDataCategoriaRepository.delete(categoriaMapperJpa.toEntity(categoria));
    }
    @Override
    public Optional<Categoria> findById(Integer id) {
        return springDataCategoriaRepository.findById(id).map(categoriaMapperJpa::toDomain);
    }

    @Override
    public Optional<Categoria> findByNombre(String nombre){
        return springDataCategoriaRepository.findByNombre(nombre).map(categoriaMapperJpa::toDomain);
    }
}
