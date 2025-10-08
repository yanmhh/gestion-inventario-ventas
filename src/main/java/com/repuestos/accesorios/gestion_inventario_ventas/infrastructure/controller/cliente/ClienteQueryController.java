package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.ClienteView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.cliente.ClienteQueryService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteQueryController {

    private final ClienteQueryService clienteQueryService;

    public ClienteQueryController(ClienteQueryService clienteQueryService) {
        this.clienteQueryService = clienteQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteView>> listarTodos() {
        List<ClienteView> clientes = clienteQueryService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteView> obtenerPorId(@PathVariable @Positive Integer id) {
        ClienteView cliente = clienteQueryService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/documento")
    public ResponseEntity<ClienteView> obtenerPorDocumento(@RequestParam String documentoIdentidad) {
        ClienteView cliente = clienteQueryService.obtenerPorDocumentoIdentidad(documentoIdentidad);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/ruc")
    public ResponseEntity<ClienteView> obtenerPorRuc(@RequestParam String rucEmpresa) {
        ClienteView cliente = clienteQueryService.obtenerPorRuc(rucEmpresa);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<ClienteView>> obtenerPorTipoCliente(@RequestParam String tipoCliente) {
        List<ClienteView> clientes = clienteQueryService.obtenerPorTipoCliente(tipoCliente);
        return ResponseEntity.ok(clientes);
    }
}
