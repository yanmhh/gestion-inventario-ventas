package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.alerta;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class AlertaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id")
    private Integer productoId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    private String mensaje;

    private Boolean leido = false;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();

    public AlertaEntity(){}

    public AlertaEntity(Integer productoId, Integer usuarioId, String mensaje) {
        this.productoId = productoId;
        this.usuarioId = usuarioId;
        this.mensaje = mensaje;
        this.leido = false;
        this.creadoEn = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}
