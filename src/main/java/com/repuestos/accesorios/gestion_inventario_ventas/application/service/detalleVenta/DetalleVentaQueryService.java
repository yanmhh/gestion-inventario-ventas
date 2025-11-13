package com.repuestos.accesorios.gestion_inventario_ventas.application.service.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta.DetalleVentaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.DetalleVentaNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta.DetalleVentaReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DetalleVentaQueryService {
    private final DetalleVentaReadRepository detalleVentaReadRepository;

    public DetalleVentaQueryService(DetalleVentaReadRepository detalleVentaReadRepository) {
        this.detalleVentaReadRepository = detalleVentaReadRepository;
    }

    public DetalleVentaView obtenerPorId(Integer id) {
        Objects.requireNonNull(id, "El ID no puede ser null");

        return detalleVentaReadRepository.findById(id)
                .map(DetalleVentaViewMapper::toView)
                .orElseThrow(() -> new DetalleVentaNoEncontradoException(
                        "DetalleVenta no encontrado con ID: " + id
                ));
    }

    public List<DetalleVentaView> obtenerPorVentaId(Integer ventaId) {
        Objects.requireNonNull(ventaId, "El ID de venta no puede ser null");

        return detalleVentaReadRepository.findByVentaId(ventaId).stream()
                .map(DetalleVentaViewMapper::toView)
                .collect(Collectors.toList());
    }

    public List<DetalleVentaView> obtenerTodos() {
        return detalleVentaReadRepository.findAll().stream()
                .map(DetalleVentaViewMapper::toView)
                .collect(Collectors.toList());
    }
}
