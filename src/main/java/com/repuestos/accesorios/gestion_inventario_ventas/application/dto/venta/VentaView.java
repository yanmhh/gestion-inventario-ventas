package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.ClienteView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioResumenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class VentaView {
    private final Integer id;

    private final ClienteView cliente;

    @JsonProperty("fecha_venta")
    private final LocalDateTime fechaVenta;

    private final String estado;

    @JsonProperty("tipo_documento")
    private final String tipoDocumento;

    private final BigDecimal total;

    private final UsuarioResumenDto usuario;

    private final String observaciones;

    @JsonProperty("detalles")
    private final List<DetalleVentaView> detalles;

    @JsonProperty("creado_en")
    private final LocalDateTime creadoEn;

    @JsonProperty("actualizado_en")
    private final LocalDateTime actualizadoEn;

}
