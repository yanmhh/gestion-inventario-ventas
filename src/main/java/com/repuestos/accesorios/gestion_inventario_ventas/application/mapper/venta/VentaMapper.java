package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.RegistroVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.EstadoVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public final class VentaMapper {
    private VentaMapper() {}

    public static Venta from(RegistroVentaDto registroVenta, Cliente cliente, Usuario usuario, List<DetalleVenta> detalles) {
        Objects.requireNonNull(registroVenta, "registroVenta no puede ser null");
        Objects.requireNonNull(cliente, "cliente no puede ser null");
        Objects.requireNonNull(usuario, "usuario no puede ser null");

        EstadoVenta estado = EstadoVenta.fromString(registroVenta.getEstado());

        Venta venta = new Venta(
                null,
                cliente,
                registroVenta.getTipoDocumento(),
                estado,
                usuario,
                registroVenta.getObservaciones(),
                LocalDateTime.now()
        );
        if (detalles != null && !detalles.isEmpty()) {
            detalles.forEach(venta::agregarDetalle);
        }

        return venta;
    }
}
