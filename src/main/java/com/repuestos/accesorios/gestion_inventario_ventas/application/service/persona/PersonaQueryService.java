package com.repuestos.accesorios.gestion_inventario_ventas.application.service.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.PersonaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.persona.PersonaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.PersonaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonaQueryService {
    private final PersonaRepository personaRepository;

    public PersonaQueryService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Transactional(readOnly = true)
    public PersonaView obtenerPorId(Integer id) {
        Objects.requireNonNull(id, "El ID no puede ser null");
        return personaRepository.findById(id).map(PersonaViewMapper::toView)
                .orElseThrow(() -> new PersonaNoEncontradaException("No se encontró una persona con ID: " + id));
    }

    @Transactional(readOnly = true)
    public PersonaView obtenerPorCorreo(String correo) {
        Objects.requireNonNull(correo, "El correo no puede ser null");
        return personaRepository.findByCorreo(correo).map(PersonaViewMapper::toView)
                .orElseThrow(() -> new PersonaNoEncontradaException("No se encontró una persona con el correo: " + correo));
    }

    @Transactional(readOnly = true)
    public List<PersonaView> listarTodas() {
        List<Persona> personas = personaRepository.findAll();
        return personas.stream().map(PersonaViewMapper::toView)
                .collect(Collectors.toList());
    }
}
