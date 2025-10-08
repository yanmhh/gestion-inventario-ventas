package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.login.LoginRequest;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.login.LoginResponse;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.RegistroUsuarioDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioView> register(@RequestBody @Valid RegistroUsuarioDto cmd) {
        authService.register(cmd);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader != null && authHeader.startsWith("Bearer ")
                ? authHeader.substring(7) : authHeader;
        return ResponseEntity.ok(authService.refresh(token));
    }
}
