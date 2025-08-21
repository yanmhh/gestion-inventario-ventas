package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.exception;

import com.repuestos.accesorios.gestion_inventario_ventas.application.service.AuthService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class ApiError {

    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;

    public ApiError(String message, String path, int status){
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

    public int getStatus() {
        return this.status;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}
