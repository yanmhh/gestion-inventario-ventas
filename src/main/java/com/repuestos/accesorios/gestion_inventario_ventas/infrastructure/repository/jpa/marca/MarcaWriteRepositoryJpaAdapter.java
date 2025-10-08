package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.marca.MarcaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MarcaWriteRepositoryJpaAdapter implements MarcaWriteRepository {

    private final SpringDataMarcaRepository springDataMarcaRepository;
    private  final MarcaMapperJpa marcaMapperJpa;

    public MarcaWriteRepositoryJpaAdapter(SpringDataMarcaRepository springDataMarcaRepository, MarcaMapperJpa marcaMapperJpa){
        this.springDataMarcaRepository = springDataMarcaRepository;
        this.marcaMapperJpa = marcaMapperJpa;
    }

    @Override
    public Marca save(Marca marca){
       return marcaMapperJpa.toDomain(springDataMarcaRepository.save(marcaMapperJpa.toEntity(marca)));
    }

    @Override
    public void delete(Marca marca){
        springDataMarcaRepository.delete(marcaMapperJpa.toEntity(marca));
    }
    @Override
    public Optional<Marca> findById(Integer id){
        return springDataMarcaRepository.findById(id).map(marcaMapperJpa::toDomain);
    }

    @Override
    public Optional<Marca> findByNombre(String nombre){
        return springDataMarcaRepository.findByNombre(nombre).map(marcaMapperJpa::toDomain);
    }


}
