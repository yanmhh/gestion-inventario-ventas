package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta.AlertaView;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.alerta.Alerta;

import java.time.format.DateTimeFormatter;

public final class AlertaViewMapper {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AlertaViewMapper() {
    }

    public static AlertaView toView(Alerta alerta) {
        String nombreCompleto = alerta.getUsuario().getPersona().getNombre() + " " +
                alerta.getUsuario().getPersona().getApellidoPaterno();
        
        return new AlertaView(
                alerta.getId(),
                alerta.getProducto().getId(),
                alerta.getProducto().getNombre(),
                alerta.getProducto().getCodigo().codigo(),
                alerta.getUsuario().getId(),
                nombreCompleto,
                alerta.getMensaje(),
                alerta.isLeido(),
                alerta.getCreadoEn().format(FORMATTER)
        );
    }
}
