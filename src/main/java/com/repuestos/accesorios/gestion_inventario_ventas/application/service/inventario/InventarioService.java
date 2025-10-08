package com.repuestos.accesorios.gestion_inventario_ventas.application.service.inventario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket.AlertaWebSocketHandler;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventarioService {
     private final ProductoWriteRepository productoWriteRepository;
     private final AlertaWebSocketHandler alertaWebSocketHandler;

    public InventarioService(ProductoWriteRepository productoWriteRepository, AlertaWebSocketHandler alertaWebSocketHandler) {
        this.productoWriteRepository = productoWriteRepository;
        this.alertaWebSocketHandler = alertaWebSocketHandler;
    }

    @Transactional
    public void incrementarStock(Integer productoId, int cantidad){
        Producto producto = productoWriteRepository.findById(productoId).orElseThrow(()->
                new ProductoNoEncontradoException("Producto con Id: " + productoId + " no encontrado."));

        producto.incrementarStock(cantidad);
        productoWriteRepository.save(producto);

        enviarAlerta("INFORMACIÓN: " , "Stock incrementado: para el producto: " + producto.getNombre() + " + " + cantidad +
                "(Stock actual: " + producto.getStock() );
    }

    @Transactional
    public void decrementarStock(Integer productoId, int cantidad){
        Producto producto = productoWriteRepository.findById(productoId).orElseThrow(()->
                new ProductoNoEncontradoException("Producto con Id: " + productoId + " no encontrado."));
        producto.decrementarStock(cantidad);
        productoWriteRepository.save(producto);

        enviarAlerta("ADVERTENCIA: " , "Stock reducido para producto: " + producto.getNombre() +
        " - " + cantidad + " (Stock actual: " + producto.getStock() + ")");

        if (producto.stockBajo()) {
            enviarAlerta("ERROR",
                    "⚠️ Stock bajo para producto: " + producto.getNombre() +
                            " (Actual: " + producto.getStock() +
                            ", Mínimo: " + producto.getStockMinimo() + ")");
        }

    }

    private void enviarAlerta(String nivel, String mensaje){
        Map<String, Object> alerta = Map.of(
                "action", "sendAlert",
                "level", nivel,
                "message", mensaje
        );

        alertaWebSocketHandler.broadcast(alerta);
    }
}
