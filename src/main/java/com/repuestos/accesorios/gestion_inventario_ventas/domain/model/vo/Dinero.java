package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MonedaInvalidaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ValorInvalidoException;

import java.math.BigDecimal;

public record Dinero(BigDecimal monto , String moneda) {
    public Dinero {
        validarMonto(monto);
        validarMoneda(moneda);
    }

    private static void validarMonto(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("El monto no puede ser nulo o negativo");
        }
    }

    private static void validarMoneda(String moneda) {
        if (moneda == null || moneda.isBlank()) {
            throw new MonedaInvalidaException("La moneda es obligatoria");
        }
    }

    public Dinero multiplicar(int cantidad) {
        return new Dinero(monto.multiply(BigDecimal.valueOf(cantidad)), moneda);
    }

    public Dinero sumar(Dinero otro) {
        if (!this.moneda.equals(otro.moneda)) {
            throw new MonedaInvalidaException("Las monedas no coinciden");
        }
        return new Dinero(this.monto.add(otro.monto), this.moneda);
    }

    public static Dinero zero(String moneda) {
        return new Dinero(BigDecimal.ZERO, moneda);
    }
}

