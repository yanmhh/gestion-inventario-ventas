package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.producto.ProductoMapperJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductoWriteRepositoryJpaAdapter implements ProductoWriteRepository {

    private final SpringDataProductoRepository springDataProductoRepository;
    private final ProductoMapperJpa productoMapperJpa;

    public ProductoWriteRepositoryJpaAdapter(SpringDataProductoRepository springDataProductoRepository, ProductoMapperJpa productoMapperJpa) {
        this.springDataProductoRepository = springDataProductoRepository;
        this.productoMapperJpa = productoMapperJpa;
    }
    @Override
    public Producto save (Producto producto){
        return productoMapperJpa.toDomain(springDataProductoRepository.save(productoMapperJpa.toEntity(producto)));
    }

    @Override
    public void delete(Producto producto){
        springDataProductoRepository.delete(productoMapperJpa.toEntity(producto));
    }

    @Override
    public Optional<Producto> findById(Integer id){
        return springDataProductoRepository.findById(id).map(productoMapperJpa::toDomain);
    }

    @Override
    public Optional<Producto> findByNombre(String nombre){
        return  springDataProductoRepository.findByNombre(nombre).map(productoMapperJpa::toDomain);
    }

    @Override
    public Optional<Producto> findByCodigo(String codigo){
        return springDataProductoRepository.findByCodigo(codigo).map(productoMapperJpa::toDomain);
    }
}
