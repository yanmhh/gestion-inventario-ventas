package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo;

public record TipoMovimientoId(Integer value) {
    public TipoMovimientoId {
        if (value < 0) {
            throw new IllegalArgumentException("El id de TipoMovimiento no puede ser negativo.");
        }
    }
}
