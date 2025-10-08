package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo;

public record MovimientoStockId(Integer value) {
    public MovimientoStockId {
        if (value < 0) {
            throw new IllegalArgumentException("El id de MovimientoStock no puede ser negativo.");
        }
    }
}
