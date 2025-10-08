package com.repuestos.accesorios.gestion_inventario_ventas.application.service.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.MovimientoStockView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.RegistroMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock.MovimientoStockMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.movimientoStock.MovimientoStockViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.inventario.InventarioService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.TipoMovimientoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.MovimientoStock;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.movimientoStock.MovimientoStockWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento.TipoMovimientoWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioWriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MovimientoStockCommandService {
    private final MovimientoStockWriteRepository movimientoStockWriteRepository;
    private final ProductoWriteRepository productoWriteRepository;
    private final TipoMovimientoWriteRepository tipoMovimientoWriteRepository;
    private final UsuarioWriteRepository usuarioWriteRepository;

    public MovimientoStockCommandService(
            MovimientoStockWriteRepository movimientoStockWriteRepository,
            ProductoWriteRepository productoWriteRepository,
            TipoMovimientoWriteRepository tipoMovimientoWriteRepository,
            UsuarioWriteRepository usuarioWriteRepository
    ) {
        this.movimientoStockWriteRepository = movimientoStockWriteRepository;
        this.productoWriteRepository = productoWriteRepository;
        this.tipoMovimientoWriteRepository = tipoMovimientoWriteRepository;
        this.usuarioWriteRepository = usuarioWriteRepository;
    }

    @Transactional
    public MovimientoStockView registrarMovimiento(RegistroMovimientoDto dto) {
        Producto producto = productoWriteRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado con ID: " + dto.getProductoId()));

        TipoMovimiento tipoMovimiento = tipoMovimientoWriteRepository.findById(dto.getTipoMovimientoId())
                .orElseThrow(() -> new TipoMovimientoNoEncontradoException("Tipo de movimiento no encontrado con ID: " + dto.getTipoMovimientoId()));

        Usuario usuario = usuarioWriteRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        MovimientoStock movimientoStock = MovimientoStockMapper.from(dto, producto, tipoMovimiento, usuario);

        movimientoStock.aplicarMovimiento();

        productoWriteRepository.save(producto);

        movimientoStock = movimientoStockWriteRepository.save(movimientoStock);

        return MovimientoStockViewMapper.toView(movimientoStock);
    }


}
