package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.RegistroVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.VentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.venta.VentaCommandService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.CustomUserDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/ventas/command")
public class VentaCommandController {
    private final VentaCommandService ventaCommandService;
    private final UsuarioWriteRepository usuarioWriteRepository;

    public VentaCommandController(VentaCommandService ventaCommandService,
                                  UsuarioWriteRepository usuarioWriteRepository) {
        this.ventaCommandService = ventaCommandService;
        this.usuarioWriteRepository = usuarioWriteRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<VentaView> registrarVenta(
            @Valid @RequestBody RegistroVentaDto registroVentaDto,
            UriComponentsBuilder uriBuilder, @AuthenticationPrincipal CustomUserDetails userDetails) {

        Usuario usuario = usuarioWriteRepository.findById(userDetails.getUsuario().getId()).
                orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontraso"));

        VentaView ventaCreada = ventaCommandService.registrarVenta(registroVentaDto, usuario);

        URI location = uriBuilder
                .path("/api/ventas/{id}")
                .buildAndExpand(ventaCreada.getId())
                .toUri();

        return ResponseEntity.created(location).body(ventaCreada);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable @Positive Integer id) {
        return ResponseEntity.noContent().build();
    }
}
