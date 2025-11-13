package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.RegistroDetalleVentaDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegistroVentaDto {

    @NotNull(message = "El ID del cliente es obligatorio.")
    @JsonProperty("cliente_id")
    private Integer clienteId;

    private LocalDateTime fecha;
    private String estado;

    @NotNull(message = "El tipo de documento es obligatorio.")
    @JsonProperty("tipo_documento")
    private String tipoDocumento;

    private BigDecimal total;

    @Size(max = 255, message = "Las observaciones no pueden superar los 255 caracteres.")
    private String observaciones;

    @JsonProperty("detalles")
    private List<RegistroDetalleVentaDto> detalles;
}
