package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductoRepositoryAdapter implements ProductoRepository {

    private final SpringDataProductoRepository springDataProductoRepository;
    private final ProductoMapperJpa productoMapperJpa;

    public JpaProductoRepositoryAdapter (SpringDataProductoRepository springDataProductoRepository, ProductoMapperJpa productoMapperJpa){
        this.springDataProductoRepository = springDataProductoRepository;
        this.productoMapperJpa = productoMapperJpa;
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

     @Override
    public List<Producto> findAll(){
        return springDataProductoRepository.findAll().stream().map(productoMapperJpa::toDomain).toList();
     }

     @Override
    public Producto save (Producto producto){
        return productoMapperJpa.toDomain(springDataProductoRepository.save(productoMapperJpa.toEntity(producto)));
     }

     @Override
    public void deleteById(Integer id){
        springDataProductoRepository.deleteById(id);
     }
}
