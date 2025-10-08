package com.repuestos.accesorios.gestion_inventario_ventas.application.dto;

import java.time.LocalDateTime;

public class AlertaDto {
    private Integer productoId;
    private Integer usuarioId;
    private String mensaje;
    private Boolean leido;
    private LocalDateTime creadoEn;

    public AlertaDto(Integer productoId, Integer usuarioId, String mensaje) {
        this.productoId = productoId;
        this.usuarioId = usuarioId;
        this.mensaje = mensaje;
        this.leido = false;
        this.creadoEn = LocalDateTime.now();
    }


    public Integer getProductoId() {
        return productoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Boolean getLeido() {
        return leido;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }



}
