package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioResumenDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.VentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.cliente.ClienteViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta.DetalleVentaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class VentaViewMapper {
    private VentaViewMapper() {}

    public static VentaView toView(Venta venta) {
        Objects.requireNonNull(venta, "Venta no puede ser null");

        List<DetalleVentaView> detallesView = venta.getDetalles().stream()
                .map(DetalleVentaViewMapper::toView)
                .collect(Collectors.toList());

        UsuarioResumenDto usuarioDto = venta.getUsuario() != null
                ? new UsuarioResumenDto(
                venta.getUsuario().getId(),
                venta.getUsuario().getPersona() != null
                        ? venta.getUsuario().getPersona().getNombre()
                        : null
        )
                : null;

        return new VentaView(
                venta.getId(),
                ClienteViewMapper.toView(venta.getCliente()),
                venta.getFechaVenta(),
                venta.getEstado().name(),
                venta.getTipoDocumento(),
                venta.getTotal(),
                usuarioDto,
                venta.getObservaciones(),
                detallesView,
                venta.getCreadoEn(),
                venta.getActualizadoEn()
        );
    }

}
