package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUsuarioRepositoryAdapter implements UsuarioRepository {

    private final SpringDataUsuarioRepository repository;
    private final UsuarioMapperJpa mapper;

    public JpaUsuarioRepositoryAdapter(SpringDataUsuarioRepository repository, UsuarioMapperJpa mapper){
    this.repository = repository;
    this.mapper = mapper;
    }

    @Override
    public Optional<Usuario> findById(Integer id){
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByEmail(String email){
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public List<Usuario> findAll(){
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Usuario save(Usuario usuario){
        return mapper.toDomain(repository.save(mapper.toEntity(usuario)));
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
