package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.usuario.UsuarioMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUsuarioReadRepositoryAdapter implements UsuarioReadRepository {

    private final SpringDataUsuarioRepository springDataUsuarioRepository;
    private final UsuarioMapperJpa usuarioMapperJpa;

    public JpaUsuarioReadRepositoryAdapter(SpringDataUsuarioRepository springDataUsuarioRepository, UsuarioMapperJpa usuarioMapperJpa){
    this.springDataUsuarioRepository = springDataUsuarioRepository;
    this.usuarioMapperJpa = usuarioMapperJpa;
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

    @Override
    public List<Usuario> findAll(){
        return springDataUsuarioRepository.findAll().stream().map(usuarioMapperJpa::toDomain).toList();
    }

    @Override
    public boolean existsById(Integer id) {
        return springDataUsuarioRepository.existsById(id);
    }

}
