package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.TipoMovimientoInvalidoException;

public enum TipoMovimiento {

   ENTRADA,
   SALIDA,
   DEVOLUCION_CLIENTE,
   DEVOLUCION_PROVEEDOR;

    public static TipoMovimiento fromString(String value) {
        try {
            return TipoMovimiento.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new TipoMovimientoInvalidoException("TipoMovimiento inválido: " + value);
        }
    }
}
