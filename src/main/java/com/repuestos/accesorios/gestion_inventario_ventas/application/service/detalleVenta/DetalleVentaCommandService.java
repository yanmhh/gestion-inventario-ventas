package com.repuestos.accesorios.gestion_inventario_ventas.application.service.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.RegistroDetalleVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta.DetalleVentaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta.DetalleVentaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta.DetalleVentaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaCommandService {
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;

    public DetalleVentaCommandService(
            DetalleVentaRepository detalleVentaRepository,
            ProductoRepository productoRepository
    ) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public DetalleVentaView registrarDetalle(RegistroDetalleVentaDto dto) {
        // Validar producto
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado con ID: " + dto.getProductoId()));

        // Convertir DTO a entidad
        DetalleVenta detalleVenta = DetalleVentaMapper.from(dto, producto);

        // Guardar en la base de datos
        detalleVenta = detalleVentaRepository.save(detalleVenta);

        // Retornar la vista
        return DetalleVentaViewMapper.toView(detalleVenta);
    }
}
