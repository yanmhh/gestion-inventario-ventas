package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.persona.PersonaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.persona.PersonaQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaQueryController {

    private final PersonaQueryService personaQueryService;

    public PersonaQueryController(PersonaQueryService personaQueryService) {
        this.personaQueryService = personaQueryService;
    }

    @GetMapping
    public ResponseEntity<List<PersonaView>> listarTodas() {
        List<PersonaView> personas = personaQueryService.listarTodas();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaView> obtenerPorId(@PathVariable @Positive Integer id) {
        PersonaView persona = personaQueryService.obtenerPorId(id);
            return ResponseEntity.ok(persona);
    }

    @GetMapping("/correo")
    public ResponseEntity<?> obtenerPorCorreo(@RequestParam String correo) {
        PersonaView persona = personaQueryService.obtenerPorCorreo(correo);
            return ResponseEntity.ok(persona);
    }
}
