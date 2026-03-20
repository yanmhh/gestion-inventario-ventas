package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.RegistroVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.VentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.venta.VentaCommandService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.CustomUserDetails;
import jakarta.validation.Valid;
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
    private final UsuarioRepository usuarioRepository;

    public VentaCommandController(VentaCommandService ventaCommandService,
                                  UsuarioRepository usuarioRepository) {
        this.ventaCommandService = ventaCommandService;
        this.usuarioRepository = usuarioRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<VentaView> registrarVenta(
            @Valid @RequestBody RegistroVentaDto registroVentaDto,
            UriComponentsBuilder uriBuilder, @AuthenticationPrincipal CustomUserDetails userDetails) {

        Usuario usuario = usuarioRepository.findById(userDetails.getUsuario().getId()).
                orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontraso"));

        VentaView ventaCreada = ventaCommandService.registrarVenta(registroVentaDto, usuario);

        URI location = uriBuilder
                .path("/api/ventas/{id}")
                .buildAndExpand(ventaCreada.getId())
                .toUri();

        return ResponseEntity.created(location).body(ventaCreada);
    }
}
