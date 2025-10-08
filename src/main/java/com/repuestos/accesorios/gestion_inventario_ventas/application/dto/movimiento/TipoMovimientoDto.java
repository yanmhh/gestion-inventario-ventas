package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento;

import java.time.LocalDateTime;

public class TipoMovimientoDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public TipoMovimientoDto(Integer id, String nombre, String descripcion,
                             LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
