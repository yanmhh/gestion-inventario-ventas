package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public class RegistroMovimientoDto {

    @NotNull(message = "El ID del producto es obligatorio.")
    @JsonProperty("producto_id")
    private Integer productoId;

    @NotNull(message = "El ID del tipo de movimiento es obligatorio.")
    @JsonProperty("tipo_movimiento_id")
    private Integer tipoMovimientoId;

    @NotNull(message = "El ID del usuario es obligatorio.")
    @JsonProperty("usuario_id")
    private Integer usuarioId;

    @Min(value = 1, message = "La cantidad debe ser mayor a 0.")
    private int cantidad;

    private LocalDateTime fecha;

    private String referencia;

    public RegistroMovimientoDto() {
    }

    public Integer getProductoId() {
        return productoId;
    }

    public Integer getTipoMovimientoId() {
        return tipoMovimientoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getReferencia() {
        return referencia;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }
}
