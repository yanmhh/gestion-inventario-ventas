package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.persona.PersonaMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonaWriteRepositoryJpaAdapter implements PersonaWriteRepository {

    private final SpringDataPersonaRepository springDataPersonaRepository;
    private final PersonaMapperJpa personaMapperJpa;

    public PersonaWriteRepositoryJpaAdapter(SpringDataPersonaRepository springDataPersonaRepository,
                                            PersonaMapperJpa personaMapperJpa) {
        this.springDataPersonaRepository = springDataPersonaRepository;
        this.personaMapperJpa = personaMapperJpa;
    }

    @Override
    public Optional<Persona> findById(Integer id) {
        return springDataPersonaRepository.findById(id)
                .map(personaMapperJpa::toDomain);
    }

    @Override
    public Optional<Persona> findByCorreo(String correo) {
        return springDataPersonaRepository.findByCorreo(correo)
                .map(personaMapperJpa::toDomain);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return springDataPersonaRepository.existsByCorreo(correo);
    }

    @Override
    public Persona save (Persona persona) {
        return personaMapperJpa.toDomain(springDataPersonaRepository.save(personaMapperJpa.toEntity(persona)));
    }

    @Override
    public void delete(Persona persona) {
        springDataPersonaRepository.delete(personaMapperJpa.toEntity(persona));
    }
}
