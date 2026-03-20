package com.repuestos.accesorios.gestion_inventario_ventas.application.service.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock.MovimientoStockMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock.MovimientoStockViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajoEvent;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock.MovimientoStockRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MovimientoStockCommandService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final ProductoRepository productoRepository;
    private final ApplicationEventPublisher eventPublisher;

    public MovimientoStockCommandService(
            MovimientoStockRepository movimientoStockRepository,
            ProductoRepository productoRepository,
            ApplicationEventPublisher eventPublisher
    ) {
        this.movimientoStockRepository = movimientoStockRepository;
        this.productoRepository = productoRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public MovimientoStockView registrarMovimiento(RegistroMovimientoDto dto, Usuario usuario) {
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new ProductoNoEncontradoException(
                        "Producto no encontrado con ID: " + dto.getProductoId()));

        MovimientoStock movimientoStock = MovimientoStockMapper.from(dto, producto, usuario);

        movimientoStock.aplicarMovimiento();

        productoRepository.save(producto);

        movimientoStock = movimientoStockRepository.save(movimientoStock);

        // Verificar si el stock está bajo y publicar evento
        if (producto.stockBajo()) {
            StockBajoEvent event = new StockBajoEvent(
                    producto,
                    producto.getStock(),
                    producto.getStockMinimo()
            );
            eventPublisher.publishEvent(event);
        }

        return MovimientoStockViewMapper.toView(movimientoStock);
    }
}
