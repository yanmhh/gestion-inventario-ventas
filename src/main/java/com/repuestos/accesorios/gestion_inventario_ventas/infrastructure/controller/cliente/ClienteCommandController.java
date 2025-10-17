package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.ClienteView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.RegistroClienteDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.cliente.ClienteCommandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/clientes/command")
public class ClienteCommandController {

    private final ClienteCommandService clienteCommandService;

    public ClienteCommandController(ClienteCommandService clienteCommandService) {
        this.clienteCommandService = clienteCommandService;
    }

    @PostMapping
    public ResponseEntity<ClienteView> crearCliente(@Valid @RequestBody RegistroClienteDto dto, UriComponentsBuilder uriBuilder) {
        ClienteView clienteView = clienteCommandService.crearCliente(dto);
        URI location = uriBuilder
                .path("/api/clientes/command/{id}")
                .buildAndExpand(clienteView.getId())
                .toUri();

        return ResponseEntity.created(location).body(clienteView);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteView> actualizarCliente(@PathVariable @Positive Integer id,
                                                         @Valid @RequestBody RegistroClienteDto dto) {
        ClienteView clienteActualizado = clienteCommandService.actualizarCliente(id, dto);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable @Positive Integer id) {
        clienteCommandService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
