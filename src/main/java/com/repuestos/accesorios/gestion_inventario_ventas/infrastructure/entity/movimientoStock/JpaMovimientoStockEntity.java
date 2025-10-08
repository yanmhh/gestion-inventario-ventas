package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.tipoMovimiento.JpaTipoMovimientoEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.usuario.JpaUsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_stock")
public class JpaMovimientoStockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_stock_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private JpaProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_movimiento_id")
    private JpaTipoMovimientoEntity tipoMovimiento;

    private int cantidad;
    private LocalDateTime fecha;
    private String referencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "usuario_id")
    private JpaUsuarioEntity usuario;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    public JpaMovimientoStockEntity(){
    }

    public Integer getId() {
        return id;
    }

    public JpaProductoEntity getProducto() {
        return producto;
    }

    public JpaTipoMovimientoEntity getTipoMovimiento() {
        return tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public JpaUsuarioEntity getUsuario() {
        return usuario;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProducto(JpaProductoEntity producto) {
        this.producto = producto;
    }

    public void setTipoMovimiento(JpaTipoMovimientoEntity tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setUsuario(JpaUsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }

    @PrePersist
    protected void onCreate(){
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }
    @PreUpdate
    protected  void onUpdate(){
        this.actualizadoEn = LocalDateTime.now();
    }
}
