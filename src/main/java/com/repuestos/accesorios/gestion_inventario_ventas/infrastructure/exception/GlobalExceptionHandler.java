package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.exception;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        ApiError apiError = new ApiError(
                "Errores en los campos enviados",
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                errores
        );

        logger.warn("Validación fallida: {}", errores);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            PersonaNoEncontradaException.class,
            UsuarioNoEncontradoException.class,
            ProductoNoEncontradoException.class,
            MarcaNoEncontradaException.class,
            CategoriaNoEncontradaException.class,
            MovimientoNoEncontradoException.class,
            MovimientoStockNoEncontradoException.class,
            TipoMovimientoNoEncontradoException.class,
            ClienteNoEncontradoException.class,
            RolNoEncontradoException.class,

    })
    public ResponseEntity<ApiError> handleNotFoundExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.warn("Recurso no encontrado: {}", ex.getMessage());

        ApiError apiError = new ApiError(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                null
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            CorreoInvalidoException.class,
            TelefonoInvalidoException.class,
            ProductoYaExisteException.class,
            ClienteYaExisteException.class,
            EmailYaRegistradoException.class
    })
    public ResponseEntity<ApiError> handleConflictExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.warn("Conflicto de datos: {}", ex.getMessage());

        ApiError apiError = new ApiError(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                null
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            NombreInvalidoException.class,
            ApellidoInvalidoException.class,
            BusinessException.class,
            ClienteInvalidoException.class,
            UsuarioInvalidoException.class,

            ValorInvalidoException.class,
            MonedaInvalidaException.class
    })
    public ResponseEntity<ApiError> handleBadRequestExceptions(RuntimeException ex, HttpServletRequest request) {
        logger.warn("Solicitud incorrecta: {}", ex.getMessage());

        ApiError apiError = new ApiError(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                null
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        logger.error("Violación de integridad de datos", ex);
        String message = "Violación de restricción de integridad de datos.";
        Throwable cause = ex.getCause();
        if (cause instanceof ConstraintViolationException sqlEx) {
            String sqlMessage = sqlEx.getSQLException().getMessage();
            if (sqlMessage.contains("persona_telefono_key")) {
                message = "Ya existe una persona registrada con este teléfono.";
            } else if (sqlMessage.contains("persona_correo_key")) {
                message = "Ya existe una persona registrada con este correo.";
            }
        }

        ApiError apiError = new ApiError(
                message,
                request.getRequestURI(),
                HttpStatus.CONFLICT.value(),
                null
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralException(Exception ex, HttpServletRequest request) {
        logger.error("Error interno del servidor", ex);
        String mensaje = "Ocurrió un error inesperado. Por favor, intente más tarde.";

        ApiError apiError = new ApiError(
                mensaje,
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<ApiError> handleUnauthorizedException(CredencialesInvalidasException ex, HttpServletRequest request) {
        logger.warn("Credenciales inválidas: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                ex.getMessage(),
                request.getRequestURI(),
                HttpStatus.UNAUTHORIZED.value(),
                null
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }
}

