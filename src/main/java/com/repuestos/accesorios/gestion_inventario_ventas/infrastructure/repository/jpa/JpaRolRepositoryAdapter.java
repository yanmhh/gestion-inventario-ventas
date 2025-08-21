package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.RolRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaRolRepositoryAdapter implements RolRepository {
    private final SpringDataRolRepository springDataRolRepository;
    private final RolMapperJpa rolMapperJpa;

    public JpaRolRepositoryAdapter(SpringDataRolRepository springDataRolRepository, RolMapperJpa rolMapperJpa) {
        this.springDataRolRepository = springDataRolRepository;
        this.rolMapperJpa = rolMapperJpa;
    }

    @Override
    public Optional<Rol> findById(Integer id) {
        return springDataRolRepository.findById(id).map(rolMapperJpa::toDomain);
    }

    @Override
    public List<Rol> findAll() {
        return springDataRolRepository.findAll()
                .stream()
                .map(rolMapperJpa::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Rol save(Rol rol) {
        var entity = rolMapperJpa.toEntity(rol);
        var savedEntity = springDataRolRepository.save(entity);
        return rolMapperJpa.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Integer id) {
        springDataRolRepository.deleteById(id);
    }
}
