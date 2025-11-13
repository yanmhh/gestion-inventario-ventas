package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.JpaVentaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "detalle_venta")
public class JpaDetalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private JpaVentaEntity venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private JpaProductoEntity producto;

    private int cantidad;
    private BigDecimal precioUnitario;

    public BigDecimal getSubTotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
