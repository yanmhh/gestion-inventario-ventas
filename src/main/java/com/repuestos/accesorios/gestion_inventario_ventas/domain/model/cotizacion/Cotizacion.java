package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cotizacion;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cotizacion {
    private final Integer id;
    private Cliente cliente;
    private LocalDateTime fechaEmision;
    private BigDecimal total;
    private String condicionesPago;
    private String notas;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Cotizacion(Integer id, Cliente cliente, LocalDateTime fechaEmision, BigDecimal total, String condicionesPago, String notas, LocalDateTime creadoEn) {
        this.id = id;
        this.cliente = cliente;
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.condicionesPago = condicionesPago;
        this.notas = notas;
        this.creadoEn = creadoEn;
    }
}
