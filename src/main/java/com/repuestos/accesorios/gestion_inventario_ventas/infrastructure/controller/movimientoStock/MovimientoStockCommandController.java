package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.movimientoStock.MovimientoStockCommandService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/movimientos-stock/command")
public class MovimientoStockCommandController {
    private final MovimientoStockCommandService movimientoStockCommandService;
    private final UsuarioWriteRepository usuarioWriteRepository;

    public MovimientoStockCommandController(MovimientoStockCommandService movimientoStockCommandService,
                                            UsuarioWriteRepository usuarioWriteRepository) {
        this.movimientoStockCommandService = movimientoStockCommandService;
        this.usuarioWriteRepository = usuarioWriteRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MovimientoStockView> registrarMovimiento(
            @RequestBody @Valid RegistroMovimientoDto dto, UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Usuario usuario = usuarioWriteRepository.findById(userDetails.getUsuario().getId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        MovimientoStockView movimientoCreado = movimientoStockCommandService.registrarMovimiento(dto, usuario);
        URI location = uriBuilder
                .path("/api/movimientos-stock/{id}")
                .buildAndExpand(movimientoCreado.getId())
                .toUri();

        return ResponseEntity.created(location).body(movimientoCreado);
    }
}
