package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoResumenDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioResumenDto;

import java.time.LocalDateTime;

public class MovimientoStockView {
    private final Integer id;
    private final ProductoResumenDto producto;
    private final TipoMovimientoDto tipoMovimiento;
    private final int cantidad;
    private final String referencia;
    private final UsuarioResumenDto usuario;
    private final LocalDateTime fecha;
    private final LocalDateTime creadoEn;
    private final LocalDateTime actualizadoEn;

    public MovimientoStockView(Integer id, ProductoResumenDto producto, TipoMovimientoDto tipoMovimiento, int cantidad, String referencia, UsuarioResumenDto usuario, LocalDateTime fecha, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        this.id = id;
        this.producto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.referencia = referencia;
        this.usuario = usuario;
        this.fecha = fecha;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return id;
    }

    public ProductoResumenDto getProducto() {
        return producto;
    }

    public TipoMovimientoDto getTipoMovimiento() {
        return tipoMovimiento;
    }

    public String getReferencia() {
        return referencia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public UsuarioResumenDto getUsuario() {
        return usuario;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }
}
