package com.repuestos.accesorios.gestion_inventario_ventas.application.service.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock.MovimientoStockMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock.MovimientoStockViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock.MovimientoStockWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoWriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MovimientoStockCommandService {
    private final MovimientoStockWriteRepository movimientoStockWriteRepository;
    private final ProductoWriteRepository productoWriteRepository;

    public MovimientoStockCommandService(
            MovimientoStockWriteRepository movimientoStockWriteRepository,
            ProductoWriteRepository productoWriteRepository
    ) {
        this.movimientoStockWriteRepository = movimientoStockWriteRepository;
        this.productoWriteRepository = productoWriteRepository;
    }

    @Transactional
    public MovimientoStockView registrarMovimiento(RegistroMovimientoDto dto, Usuario usuario) {
        Producto producto = productoWriteRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado con ID: " + dto.getProductoId()));

        MovimientoStock movimientoStock = MovimientoStockMapper.from(dto, producto, usuario);

        movimientoStock.aplicarMovimiento();

        productoWriteRepository.save(producto);

        movimientoStock = movimientoStockWriteRepository.save(movimientoStock);

        return MovimientoStockViewMapper.toView(movimientoStock);
    }
}
