package com.repuestos.accesorios.gestion_inventario_ventas.application.service.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoFilter;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto.ProductoViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoReadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoQueryService {
    private final ProductoReadRepository productoReadRepository;

    public ProductoQueryService(ProductoReadRepository productoReadRepository) {
        this.productoReadRepository = productoReadRepository;
    }

    public Page<ProductoView> listarTodosLosProductos(Pageable pageable){
        return productoReadRepository.findAll(pageable)
                .map(ProductoViewMapper::toView);
    }

    public ProductoView obtenerProductoPorId(Integer id) {
        Producto producto = productoReadRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado"));

        return ProductoViewMapper.toView(producto);
    }

    public Page<ProductoView> buscarPorFiltro(ProductoFilter filtro, Pageable pageable) {
        return productoReadRepository.findAllConFiltro(filtro, pageable)
                .map(ProductoViewMapper::toView);
    }
}
