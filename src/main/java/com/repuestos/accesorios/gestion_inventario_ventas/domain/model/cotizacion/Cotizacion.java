package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cotizacion;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ValorInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.Dinero;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cotizacion {

    private final Integer id;
    private final Cliente cliente;
    private LocalDateTime fechaEmision;
    private Dinero total;
    private Dinero moneda;
    private String condicionesPago;
    private String notas;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    private final List<DetalleCotizacion> items = new ArrayList<>();


    public Cotizacion(Integer id, Cliente cliente, LocalDateTime fechaEmision, Dinero total,
                      Dinero moneda, String condicionesPago, String notas, LocalDateTime creadoEn) {

        validarCliente(cliente);
        validarFecha(fechaEmision);
        validarMoneda(moneda);

        this.id = id;
        this.cliente = cliente;
        this.fechaEmision = fechaEmision;
        this.total = total;
        this.moneda = moneda;
        this.condicionesPago = condicionesPago;
        this.notas = notas;
        this.creadoEn = creadoEn;
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new ValorInvalidoException("El cliente es obligatorio");
        }
    }

    private void validarFecha(LocalDateTime fecha) {
        if (fecha == null) {
            throw new ValorInvalidoException("La fecha de emisión es obligatoria");
        }
    }

    private void validarMoneda(Dinero moneda) {
        if (moneda == null) {
            throw new ValorInvalidoException("La moneda es obligatoria");
        }
    }

    public void agregarItem(DetalleCotizacion item) {
        if (item == null) throw new ValorInvalidoException("El item no puede ser nulo");

        this.items.add(item);
        recalcularTotal();
        this.actualizadoEn = LocalDateTime.now();
    }

    public void eliminarItem(DetalleCotizacion item) {
        if (item == null) throw new ValorInvalidoException("El item no puede ser nulo");

        this.items.remove(item);
        recalcularTotal();
        this.actualizadoEn = LocalDateTime.now();
    }

    private void recalcularTotal() {
        this.total = items.stream()
                .map(DetalleCotizacion::subtotal)
                .reduce(Dinero.zero(this.moneda.moneda()), Dinero::sumar);
    }

    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public Dinero getTotal() {
        return total;
    }

    public Dinero getMoneda() {
        return moneda;
    }

    public String getCondicionesPago() {
        return condicionesPago;
    }

    public String getNotas() {
        return notas;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public List<DetalleCotizacion> getItems() {
        return items;
    }
}
