package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.Dinero;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class venta {

    private final Integer id;
    private Cliente cliente ;
    private LocalDateTime fechaVenta;
    private EstadoVenta estado;
    private String tipoDocumento;
    private Dinero moneda;
    private BigDecimal total;
    private Usuario usuario;
    private String observaciones;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public venta(Integer id, Cliente cliente, String tipoDocumento, EstadoVenta estado, BigDecimal total, Usuario usuario, String observaciones, LocalDateTime creadoEn) {
        this.id = id;
        this.cliente = cliente;
        this.tipoDocumento = tipoDocumento;
        this.estado = estado;
        this.total = total;
        this.usuario = usuario;
        this.observaciones = observaciones;
        this.creadoEn = creadoEn;
    }
}
