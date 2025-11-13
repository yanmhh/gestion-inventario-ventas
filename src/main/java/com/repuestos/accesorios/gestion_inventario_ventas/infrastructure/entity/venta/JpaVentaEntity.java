package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.cliente.JpaClienteEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.detalleVenta.JpaDetalleVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.usuario.JpaUsuarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "venta")
public class JpaVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private JpaClienteEntity cliente;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoVentaEntity estado;

    @Column(name = "tipo_documento", length = 50)
    private String tipoDocumento;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private JpaUsuarioEntity usuario;

    private String observaciones;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JpaDetalleVentaEntity> detalles = new ArrayList<>();

    public void agregarDetalle(JpaDetalleVentaEntity detalle) {
        detalle.setVenta(this);
        this.detalles.add(detalle);
        this.total = detalles.stream()
                .map(JpaDetalleVentaEntity::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PrePersist
    public void prePersist() {
        this.creadoEn = LocalDateTime.now();
        this.fechaVenta = this.fechaVenta != null ? this.fechaVenta : LocalDateTime.now();
        this.total = this.total != null ? this.total : BigDecimal.ZERO;
    }

    @PreUpdate
    public void preUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }
}
