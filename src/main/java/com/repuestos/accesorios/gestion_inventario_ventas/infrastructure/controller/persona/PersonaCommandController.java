package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.PersonaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.RegistroPersonaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.persona.PersonaCommandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/personas")
public class PersonaCommandController {
    private final PersonaCommandService personaCommandService;

    public PersonaCommandController(PersonaCommandService personaCommandService) {
        this.personaCommandService = personaCommandService;
    }

    @PostMapping
    public ResponseEntity<PersonaView> registrarPersona (@Valid @RequestBody RegistroPersonaDto dto, UriComponentsBuilder uriBuilder) {
        PersonaView personaView = personaCommandService.registrarPersona(dto);
        URI location = uriBuilder
                .path("/api/personas/{id}")
                .buildAndExpand(personaView.getId())
                .toUri();

        return ResponseEntity.created(location).body(personaView);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable @Positive Integer id) {
        personaCommandService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
