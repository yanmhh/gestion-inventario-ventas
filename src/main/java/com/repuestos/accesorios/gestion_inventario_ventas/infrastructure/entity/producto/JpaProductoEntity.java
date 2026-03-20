package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.categoria.JpaCategoriaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.marca.JpaMarcaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class JpaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @Column(name = "costo_compra")
    private BigDecimal costoCompra;

    @Column(name = "stock")
    private int stock;

    @Column(name = "stock_minimo")
    private int stockMinimo;

    @Column(name = "codigo", nullable = false , unique = true, length = 50)
    private String codigo;

    @Column (name = "imagen_url", length = 500)
    private String imagenUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn ( name = "marca_id")
    private JpaMarcaEntity marca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn ( name = "categoria_id")
    private JpaCategoriaEntity categoria;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

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
