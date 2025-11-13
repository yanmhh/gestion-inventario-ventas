package com.repuestos.accesorios.gestion_inventario_ventas.application.service.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.VentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.venta.VentaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.VentaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta.VentaReadRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class VentaQueryService {
    private final VentaReadRepository ventaReadRepository;

    public VentaQueryService(VentaReadRepository ventaReadRepository) {
        this.ventaReadRepository = ventaReadRepository;
    }

    public VentaView obtenerPorId(Integer id) {
        Objects.requireNonNull(id, "El ID no puede ser null");
        return ventaReadRepository.findById(id)
                .map(VentaViewMapper::toView)
                .orElseThrow(() -> new VentaNoEncontradaException("Venta no encontrada con ID: " + id));
    }

    public List<VentaView> obtenerTodas() {
        return ventaReadRepository.findAll().stream()
                .map(VentaViewMapper::toView)
                .collect(Collectors.toList());
    }
}
