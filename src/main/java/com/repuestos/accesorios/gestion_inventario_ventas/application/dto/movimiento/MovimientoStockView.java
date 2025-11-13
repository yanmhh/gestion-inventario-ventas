package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoResumenDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioResumenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class MovimientoStockView {
    private final Integer id;
    private final ProductoResumenDto producto;
    private final String tipoMovimiento;
    private final int cantidad;
    private final String referencia;
    private final UsuarioResumenDto usuario;
    private final LocalDateTime fecha;
    private final LocalDateTime creadoEn;
    private final LocalDateTime actualizadoEn;

}
