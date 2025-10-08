package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.persona.PersonaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonaReadRepositoryJpaAdapter implements PersonaReadRepository {
    private final SpringDataPersonaRepository springDataPersonaRepository;

    public PersonaReadRepositoryJpaAdapter(SpringDataPersonaRepository springDataPersonaRepository) {
        this.springDataPersonaRepository = springDataPersonaRepository;
    }

    @Override
    public Optional<Persona> findById(Integer id) {
        return springDataPersonaRepository.findById(id)
                .map(PersonaMapperJpa::toDomain);
    }

    @Override
    public Optional<Persona> findByCorreo(String correo) {
        return springDataPersonaRepository.findByCorreo(correo)
                .map(PersonaMapperJpa::toDomain);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return springDataPersonaRepository.existsByCorreo(correo);
    }

    @Override
    public List<Persona> findAll() {
        return springDataPersonaRepository.findAll()
                .stream()
                .map(PersonaMapperJpa::toDomain)
                .collect(Collectors.toList());
    }
}
