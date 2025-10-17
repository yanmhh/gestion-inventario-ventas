package com.repuestos.accesorios.gestion_inventario_ventas.application.service.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.RegisterProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.UpdateProductoCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto.ProductoMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.producto.ProductoViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.CategoriaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MarcaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoYaExisteException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoWriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ProductoCommandService {
    private final ProductoWriteRepository productoWriteRepository;
    private final MarcaWriteRepository marcaWriteRepository;
    private final CategoriaWriteRepository categoriaWriteRepository;

    public ProductoCommandService(ProductoWriteRepository productoWriteRepository, MarcaWriteRepository marcaWriteRepository, CategoriaWriteRepository categoriaWriteRepository){
        this.productoWriteRepository = productoWriteRepository;
        this.marcaWriteRepository = marcaWriteRepository;
        this.categoriaWriteRepository = categoriaWriteRepository;
    }

    @Transactional
    public ProductoView registrarProducto(RegisterProductoCommand registerRequest){
        verificarCodigoUnico(registerRequest.getCodigo(), null);

        Marca marca = marcaWriteRepository.findById(registerRequest.getMarcaId())
                .orElseThrow(()->new MarcaNoEncontradaException(
                        "Marca no encontrada con ID" + registerRequest.getMarcaId()));

        Categoria categoria = categoriaWriteRepository.findById(registerRequest.getCategoriaId())
                .orElseThrow(() -> new CategoriaNoEncontradaException(
                        "Categoría no encontrada con ID " + registerRequest.getCategoriaId()));

        Producto producto = ProductoMapper.from(registerRequest, marca ,categoria);
        producto = productoWriteRepository.save(producto);
        return ProductoViewMapper.toView(producto);
    }

    @Transactional
    public ProductoView actualizarProducto(Integer id, UpdateProductoCommand updateRequest){
        Producto productoExistente = productoWriteRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(String.format("Producto con ID %d no encontrado", id)));

        verificarCodigoUnico(updateRequest.getCodigo(), id);
        Marca marca = marcaWriteRepository.findById(updateRequest.getMarcaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Marca no encontrada con ID " + updateRequest.getMarcaId()));

        Categoria categoria = categoriaWriteRepository.findById(updateRequest.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Categoría no encontrada con ID " + updateRequest.getCategoriaId()));

        ProductoMapper.mapUpdateData(updateRequest, productoExistente, marca, categoria);
        productoExistente = productoWriteRepository.save(productoExistente);

        return ProductoViewMapper.toView(productoExistente);
    }

    @Transactional
    public void eliminarProducto(Integer id){
        Producto producto = productoWriteRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado."));
        productoWriteRepository.delete(producto);
    }

    private void verificarCodigoUnico(String codigo, Integer idExcluido){
        productoWriteRepository.findByCodigo(codigo)
                .ifPresent(producto -> {
                    if (!producto.getId().equals(idExcluido)) {
                        throw new ProductoYaExisteException("El código ya está en uso por otro producto.");
                    }
                });
    }

}
