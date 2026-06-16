package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.persona.JpaPersonaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.persona.PersonaMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonaRepositoryJpaAdapter extends BaseRepositoryAdapter<Persona, JpaPersonaEntity, Integer>
        implements PersonaRepository {

    private final PersonaMapperJpa personaMapperJpa;

    public PersonaRepositoryJpaAdapter(SpringDataPersonaRepository springDataPersonaRepository,
                                       PersonaMapperJpa personaMapperJpa) {
        super(springDataPersonaRepository);
        this.personaMapperJpa = personaMapperJpa;
    }

    @Override
    protected Persona toDomain(JpaPersonaEntity entity) {
        return personaMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaPersonaEntity toEntity(Persona domain) {
        return personaMapperJpa.toEntity(domain);
    }

    @Override
    public Optional<Persona> findByCorreo(String correo) {
        return ((SpringDataPersonaRepository) repository).findByCorreo(correo)
                .map(this::toDomain);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return ((SpringDataPersonaRepository) repository).existsByCorreo(correo);
    }

    @Override
    public List<Persona> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Persona save(Persona persona) {
        JpaPersonaEntity entity = toEntity(persona);
        JpaPersonaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Persona persona) {
        JpaPersonaEntity entity = toEntity(persona);
        repository.delete(entity);
    }
}
