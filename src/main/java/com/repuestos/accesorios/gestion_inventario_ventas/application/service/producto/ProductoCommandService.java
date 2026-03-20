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
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ProductoCommandService {
    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoCommandService(ProductoRepository productoRepository, MarcaRepository marcaRepository, CategoriaRepository categoriaRepository){
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ProductoView registrarProducto(RegisterProductoCommand registerRequest){
        verificarCodigoUnico(registerRequest.getCodigo(), null);

        Marca marca = marcaRepository.findById(registerRequest.getMarcaId())
                .orElseThrow(()->new MarcaNoEncontradaException(
                        "Marca no encontrada con ID" + registerRequest.getMarcaId()));

        Categoria categoria = categoriaRepository.findById(registerRequest.getCategoriaId())
                .orElseThrow(() -> new CategoriaNoEncontradaException(
                        "Categoría no encontrada con ID " + registerRequest.getCategoriaId()));

        Producto producto = ProductoMapper.from(registerRequest, marca ,categoria);
        producto = productoRepository.save(producto);
        return ProductoViewMapper.toView(producto);
    }

    @Transactional
    public ProductoView actualizarProducto(Integer id, UpdateProductoCommand updateRequest){
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(String.format("Producto con ID %d no encontrado", id)));

        verificarCodigoUnico(updateRequest.getCodigo(), id);
        Marca marca = marcaRepository.findById(updateRequest.getMarcaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Marca no encontrada con ID " + updateRequest.getMarcaId()));

        Categoria categoria = categoriaRepository.findById(updateRequest.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Categoría no encontrada con ID " + updateRequest.getCategoriaId()));

        ProductoMapper.mapUpdateData(updateRequest, productoExistente, marca, categoria);
        productoExistente = productoRepository.save(productoExistente);

        return ProductoViewMapper.toView(productoExistente);
    }

    @Transactional
    public void eliminarProducto(Integer id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado."));
        productoRepository.delete(producto);
    }

    private void verificarCodigoUnico(String codigo, Integer idExcluido){
        productoRepository.findByCodigo(codigo)
                .ifPresent(producto -> {
                    if (!producto.getId().equals(idExcluido)) {
                        throw new ProductoYaExisteException("El código ya está en uso por otro producto.");
                    }
                });
    }

}
