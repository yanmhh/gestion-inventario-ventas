package com.repuestos.accesorios.gestion_inventario_ventas.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RegisterProductoCommand {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precio;

    @Min(0)
    private int stock;

    @NotBlank
    private String codigo;

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public BigDecimal getPrecio() {
        return this.precio;
    }

    public int getStock() {
        return this.stock;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
