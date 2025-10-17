package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.marca.MarcaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MarcaReadRepositoryJpdAdapter implements MarcaReadRepository {
    private final SpringDataMarcaRepository springDataMarcaRepository;
    private final MarcaMapperJpa marcaMapperJpa;

    public MarcaReadRepositoryJpdAdapter(SpringDataMarcaRepository springDataMarcaRepository, MarcaMapperJpa marcaMapperJpa){
        this.springDataMarcaRepository = springDataMarcaRepository;
        this.marcaMapperJpa = marcaMapperJpa;
    }

    @Override
    public Optional<Marca> findById(Integer id){
        return springDataMarcaRepository.findById(id).map(marcaMapperJpa::toDomain);
    }

    @Override
    public Optional<Marca> findByNombre(String nombre){
        return springDataMarcaRepository.findByNombre(nombre).map(marcaMapperJpa::toDomain);
    }

    @Override
    public List<Marca> findAll(){
        return springDataMarcaRepository.findAll().stream().map(marcaMapperJpa::toDomain).toList();
    }
}
