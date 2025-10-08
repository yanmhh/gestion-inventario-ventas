package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.persona.PersonaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonaWriteRepositoryJpaAdapter implements PersonaWriteRepository {

    private final SpringDataPersonaRepository springDataPersonaRepository;

    public PersonaWriteRepositoryJpaAdapter(SpringDataPersonaRepository springDataPersonaRepository) {
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
    public Persona save (Persona persona) {
        return PersonaMapperJpa.toDomain(springDataPersonaRepository.save(PersonaMapperJpa.toEntity(persona)));
    }

    @Override
    public void delete(Persona persona) {
        springDataPersonaRepository.delete(PersonaMapperJpa.toEntity(persona));
    }
}
