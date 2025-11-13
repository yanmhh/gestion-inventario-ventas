package com.repuestos.accesorios.gestion_inventario_ventas.application.service.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.RegistroVentaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.venta.VentaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.detalleVenta.DetalleVentaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.venta.VentaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.venta.VentaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ClienteNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.DetalleVenta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.venta.Venta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente.ClienteReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.venta.VentaWriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaCommandService {
    private final VentaWriteRepository ventaWriteRepository;
    private final ClienteReadRepository clienteReadRepository;
    private final ProductoReadRepository productoReadRepository;

    public VentaCommandService(
            VentaWriteRepository ventaWriteRepository,
            ClienteReadRepository clienteReadRepository,
            ProductoReadRepository productoReadRepository
    ) {
        this.ventaWriteRepository = ventaWriteRepository;
        this.clienteReadRepository = clienteReadRepository;
        this.productoReadRepository = productoReadRepository;
    }

    @Transactional
    public VentaView registrarVenta(RegistroVentaDto dto, Usuario usuario) {
        Cliente cliente = clienteReadRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteNoEncontradoException(
                        "Cliente no encontrado con ID: " + dto.getClienteId()
                ));

        List<DetalleVenta> detalles = dto.getDetalles().stream()
                .map(detalleDto -> {
                    Producto producto = productoReadRepository.findById(detalleDto.getProductoId())
                            .orElseThrow(() -> new ProductoNoEncontradoException(
                                    "Producto no encontrado con ID: " + detalleDto.getProductoId()
                            ));
                    return DetalleVentaMapper.from(detalleDto, producto);
                })
                .collect(Collectors.toList());

        Venta venta = VentaMapper.from(dto, cliente, usuario, detalles);

        venta = ventaWriteRepository.save(venta);

        return VentaViewMapper.toView(venta);
    }
}
