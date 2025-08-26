package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUsuarioRepositoryAdapter implements UsuarioRepository {

    private final SpringDataUsuarioRepository springDataUsuarioRepository;
    private final UsuarioMapperJpa usuarioMapperJpa;

    public JpaUsuarioRepositoryAdapter(SpringDataUsuarioRepository springDataUsuarioRepository, UsuarioMapperJpa usuarioMapperJpa){
    this.springDataUsuarioRepository = springDataUsuarioRepository;
    this.usuarioMapperJpa = usuarioMapperJpa;
    }

    @Override
    public Optional<Usuario> findById(Integer id){
        return springDataUsuarioRepository.findById(id).map(usuarioMapperJpa::toDomain);
    }

    @Override
    public Optional<Usuario> findByEmail(String email){
        return springDataUsuarioRepository.findByEmail(email).map(usuarioMapperJpa::toDomain);
    }

    @Override
    public List<Usuario> findAll(){
        return springDataUsuarioRepository.findAll().stream().map(usuarioMapperJpa::toDomain).toList();
    }

    @Override
    public Usuario save(Usuario usuario){
        return usuarioMapperJpa.toDomain(springDataUsuarioRepository.save(usuarioMapperJpa.toEntity(usuario)));
    }

    @Override
    public void deleteById(Integer id) {
        springDataUsuarioRepository.deleteById(id);
    }
}
