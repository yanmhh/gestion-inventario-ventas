package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.usuario.JpaUsuarioEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.usuario.UsuarioMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryJpaAdapter extends BaseRepositoryAdapter<Usuario, JpaUsuarioEntity, Integer>
        implements UsuarioRepository {

    private final UsuarioMapperJpa usuarioMapperJpa;

    public UsuarioRepositoryJpaAdapter(SpringDataUsuarioRepository springDataUsuarioRepository,
                                       UsuarioMapperJpa usuarioMapperJpa) {
        super(springDataUsuarioRepository);
        this.usuarioMapperJpa = usuarioMapperJpa;
    }

    @Override
    protected Usuario toDomain(JpaUsuarioEntity entity) {
        return usuarioMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaUsuarioEntity toEntity(Usuario domain) {
        return usuarioMapperJpa.toEntity(domain);
    }

    @Override
    public Optional<Usuario> findByPersonaId(Integer personaId) {
        return ((SpringDataUsuarioRepository) repository).findByPersonaId(personaId)
                .map(this::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Usuario save(Usuario usuario) {
        JpaUsuarioEntity entity = toEntity(usuario);
        JpaUsuarioEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Usuario usuario) {
        JpaUsuarioEntity entity = toEntity(usuario);
        repository.delete(entity);
    }
}
