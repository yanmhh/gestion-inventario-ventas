package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.usuario.UsuarioMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class JpaUsuarioWriteRepositoryAdapter implements UsuarioWriteRepository {
    private final SpringDataUsuarioRepository springDataUsuarioRepository;
    private final UsuarioMapperJpa usuarioMapperJpa;

    public JpaUsuarioWriteRepositoryAdapter(SpringDataUsuarioRepository springDataUsuarioRepository, UsuarioMapperJpa usuarioMapperJpa) {
        this.springDataUsuarioRepository = springDataUsuarioRepository;
        this.usuarioMapperJpa = usuarioMapperJpa;
    }

    @Override
    public Usuario save(Usuario usuario){
        return usuarioMapperJpa.toDomain(springDataUsuarioRepository.save(usuarioMapperJpa.toEntity(usuario)));
    }

    @Override
    public void delete(Usuario usuario) {
        springDataUsuarioRepository.delete(usuarioMapperJpa.toEntity(usuario));
    }

    @Override
    public Optional<Usuario> findById(Integer id){
        return springDataUsuarioRepository.findById(id).map(usuarioMapperJpa::toDomain);
    }

    @Override
    public Optional<Usuario> findByPersonaId(Integer personaId) {
        return springDataUsuarioRepository.findByPersonaId(personaId)
                .map(usuarioMapperJpa::toDomain);
    }
}
