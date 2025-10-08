package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.categoria.CategoriaMapperJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoriaReadRepositoryJpaAdapter implements CategoriaReadRepository {
    private final SpringDataCategoriaRepository springDataCategoriaRepository;
    private final CategoriaMapperJpa categoriaMapperJpa;

    public CategoriaReadRepositoryJpaAdapter( SpringDataCategoriaRepository springDataCategoriaRepository, CategoriaMapperJpa categoriaMapperJpa){
        this.springDataCategoriaRepository = springDataCategoriaRepository;
        this.categoriaMapperJpa = categoriaMapperJpa;
    }

    @Override
    public Optional<Categoria> findById(Integer id){
        return springDataCategoriaRepository.findById(id).map(categoriaMapperJpa::toDomain);
    }

    @Override
    public Optional<Categoria> findByNombre(String nombre){
        return springDataCategoriaRepository.findByNombre(nombre).map(categoriaMapperJpa::toDomain);
    }

    @Override
    public Page<Categoria> findAll(Pageable pageable){
        return springDataCategoriaRepository.findAll(pageable).map(categoriaMapperJpa::toDomain);
    }
}
