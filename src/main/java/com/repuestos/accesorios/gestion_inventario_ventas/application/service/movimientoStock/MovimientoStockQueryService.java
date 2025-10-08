package com.repuestos.accesorios.gestion_inventario_ventas.application.service.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock.MovimientoStockViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MovimientoStockNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock.MovimientoStockReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovimientoStockQueryService {
    private final MovimientoStockReadRepository movimientoStockReadRepository;

    public MovimientoStockQueryService(MovimientoStockReadRepository movimientoStockReadRepository) {
        this.movimientoStockReadRepository = movimientoStockReadRepository;
    }

    public MovimientoStockView obtenerPorId(Integer id){
        Objects.requireNonNull(id, "El ID no puede ser null");
        return movimientoStockReadRepository.findById(id).map(MovimientoStockViewMapper::toView).
                orElseThrow(() -> new MovimientoStockNoEncontradoException("Movimiento de Stock no encontrado con ID: " + id));
    }
    public List<MovimientoStockView> obtenerTodosLosMovimientos(){
        return  movimientoStockReadRepository.findAll().stream().map(MovimientoStockViewMapper::toView)
                .collect(Collectors.toList());
    }

    public List<MovimientoStockView> obtenerMovimientosPorProductoId(Integer productoId) {
        Objects.requireNonNull(productoId, "El ID del producto no puede ser null");
        return movimientoStockReadRepository.findByProductoId(productoId)
                .stream()
                .map(MovimientoStockViewMapper::toView)
                .collect(Collectors.toList());
    }
}
