package com.repuestos.accesorios.gestion_inventario_ventas.application.service.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.DetalleVentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.detalleVenta.RegistroDetalleVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta.DetalleVentaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta.DetalleVentaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.detalleVenta.DetalleVentaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoReadRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaCommandService {
    private final DetalleVentaWriteRepository detalleVentaWriteRepository;
    private final ProductoReadRepository productoReadRepository;

    public DetalleVentaCommandService(
            DetalleVentaWriteRepository detalleVentaWriteRepository,
            ProductoReadRepository productoReadRepository
    ) {
        this.detalleVentaWriteRepository = detalleVentaWriteRepository;
        this.productoReadRepository = productoReadRepository;
    }

    @Transactional
    public DetalleVentaView registrarDetalle(RegistroDetalleVentaDto dto) {
        // Validar producto
        Producto producto = productoReadRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado con ID: " + dto.getProductoId()));

        // Convertir DTO a entidad
        DetalleVenta detalleVenta = DetalleVentaMapper.from(dto, producto);

        // Guardar en la base de datos
        detalleVenta = detalleVentaWriteRepository.save(detalleVenta);

        // Retornar la vista
        return DetalleVentaViewMapper.toView(detalleVenta);
    }
}
