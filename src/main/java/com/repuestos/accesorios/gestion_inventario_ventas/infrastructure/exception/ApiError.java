package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiError {

    private final String message;
    private final String path;
    private final int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timestamp;
    private final Object details;

    public ApiError(String message, String path, int status, Object details) {
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Object getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", details=" + details +
                '}';
    }
}
