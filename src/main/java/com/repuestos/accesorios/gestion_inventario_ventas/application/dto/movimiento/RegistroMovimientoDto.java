package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RegistroMovimientoDto {

    @NotNull(message = "El ID del producto es obligatorio.")
    @JsonProperty("producto_id")
    private Integer productoId;

    @NotNull(message = "Es obligatorio.")
    @JsonProperty("tipo_movimiento")
    private String tipoMovimiento;

    @Min(value = 1, message = "La cantidad debe ser mayor a 0.")
    private int cantidad;

    private LocalDateTime fecha;

    private String referencia;

}
