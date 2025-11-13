package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.VentaInvalidaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private final Integer id;
    private Cliente cliente ;
    private LocalDateTime fechaVenta;
    private EstadoVenta estado;
    private String tipoDocumento;
    private BigDecimal total;
    private Usuario usuario;
    private String observaciones;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    private final List<DetalleVenta> detalles = new ArrayList<>();

    public Venta(Integer id, Cliente cliente, String tipoDocumento, EstadoVenta estado,
                 Usuario usuario, String observaciones, LocalDateTime creadoEn) {

        validarCliente(cliente);
        validarUsuario(usuario);
        validarTipoDocumento(tipoDocumento);

        this.id = id;
        this.cliente = cliente;
        this.tipoDocumento = tipoDocumento;
        this.estado = (estado != null) ? estado : EstadoVenta.PENDIENTE;
        this.usuario = usuario;
        this.observaciones = observaciones;
        this.creadoEn = (creadoEn != null) ? creadoEn : LocalDateTime.now();
        this.fechaVenta = LocalDateTime.now();
        this.total = BigDecimal.ZERO;
    }
    public void agregarDetalle(DetalleVenta detalle) {
        validarDetalle(detalle);
        detalles.add(detalle);
        recalcularTotal();
    }

    public void confirmar() {
        if (detalles.isEmpty()) {
            throw new VentaInvalidaException("No se puede confirmar una venta sin detalles.");
        }
        this.estado = EstadoVenta.CONFIRMADA;
        this.actualizadoEn = LocalDateTime.now();
    }

    public void anular() {
        if (estado == EstadoVenta.ANULADA) {
            throw new VentaInvalidaException("La venta ya está anulada.");
        }
        this.estado = EstadoVenta.ANULADA;
        this.actualizadoEn = LocalDateTime.now();
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new VentaInvalidaException("El cliente es obligatorio.");
        }
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new VentaInvalidaException("El usuario responsable es obligatorio.");
        }
    }

    private void validarTipoDocumento(String tipoDocumento) {
        if (tipoDocumento == null || tipoDocumento.isBlank()) {
            throw new VentaInvalidaException("El tipo de documento es obligatorio.");
        }
    }

    private void validarDetalle(DetalleVenta detalle) {
        if (detalle == null) {
            throw new VentaInvalidaException("El detalle de venta no puede ser nulo.");
        }
        if (estado != EstadoVenta.PENDIENTE) {
            throw new VentaInvalidaException("No se pueden agregar detalles a una venta confirmada o anulada.");
        }
    }

    private void recalcularTotal() {
        this.total = detalles.stream()
                .map(DetalleVenta::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Usuario getUsuario() { return usuario; }
    public EstadoVenta getEstado() { return estado; }
    public BigDecimal getTotal() { return total; }
    public List<DetalleVenta> getDetalles() { return List.copyOf(detalles); }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public String getTipoDocumento() { return tipoDocumento; }
    public String getObservaciones() { return observaciones; }
    public LocalDateTime getCreadoEn() { return creadoEn; }
    public LocalDateTime getActualizadoEn() { return actualizadoEn; }
}
