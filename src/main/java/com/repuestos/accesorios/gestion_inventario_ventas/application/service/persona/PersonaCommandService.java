package com.repuestos.accesorios.gestion_inventario_ventas.application.service.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.PersonaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.RegistroPersonaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona.PersonaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona.PersonaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.CorreoInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.PersonaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaWriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaCommandService {

    private final PersonaWriteRepository personaWriteRepository;

    public PersonaCommandService(PersonaWriteRepository personaWriteRepository) {
        this.personaWriteRepository = personaWriteRepository;
    }

    @Transactional
    public PersonaView registrarPersona(RegistroPersonaDto dto) {
        if (personaWriteRepository.existePorCorreo(dto.getCorreo())) {
            throw new CorreoInvalidoException("Ya existe una persona registrada con este correo");
        }

        Persona persona = PersonaMapper.toDomain(dto);
        persona = personaWriteRepository.save(persona);
        return PersonaViewMapper.toView(persona);
    }

    @Transactional
    public PersonaView actualizrPersona(Integer id, RegistroPersonaDto dto) {
        Persona personaExistente = personaWriteRepository.findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException("Persona con ID " + id + " no encontrada."));

        if (!personaExistente.getCorreo().equals(dto.getCorreo()) &&
                personaWriteRepository.existePorCorreo(dto.getCorreo())) {
            throw new CorreoInvalidoException("Ya existe una persona registrada con este correo");
        }

        PersonaMapper.mapUpdateData(dto, personaExistente);
        personaExistente = personaWriteRepository.save(personaExistente);
        return PersonaViewMapper.toView(personaExistente);
    }

    @Transactional
    public void eliminarPersona(Integer id) {
        Persona persona = personaWriteRepository.findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException("Persona con ID " + id + " no encontrada."));
        personaWriteRepository.delete(persona);
    }
}
