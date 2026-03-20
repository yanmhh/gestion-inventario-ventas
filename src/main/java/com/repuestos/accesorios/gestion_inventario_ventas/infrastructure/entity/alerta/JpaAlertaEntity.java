package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.usuario.JpaUsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class JpaAlertaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private JpaProductoEntity producto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private JpaUsuarioEntity usuario;

    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "leido", nullable = false)
    private boolean leido;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn;

    public JpaAlertaEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JpaProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(JpaProductoEntity producto) {
        this.producto = producto;
    }

    public JpaUsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(JpaUsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}
