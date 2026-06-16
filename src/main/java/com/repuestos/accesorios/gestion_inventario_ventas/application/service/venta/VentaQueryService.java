package com.repuestos.accesorios.gestion_inventario_ventas.application.service.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.VentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.venta.VentaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.VentaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class VentaQueryService {
    private final VentaRepository ventaRepository;

    public VentaQueryService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public VentaView obtenerPorId(Integer id) {
        Objects.requireNonNull(id, "El ID no puede ser null");
        return ventaRepository.findById(id)
                .map(VentaViewMapper::toView)
                .orElseThrow(() -> new VentaNoEncontradaException("Venta no encontrada con ID: " + id));
    }

    public List<VentaView> obtenerTodas() {
        return ventaRepository.findAll().stream()
                .map(VentaViewMapper::toView)
                .collect(Collectors.toList());
    }
}
