package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoFilter;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.producto.ProductoMapperJpa;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductoReadRepositoryJpaAdapter implements ProductoReadRepository {

    private final SpringDataProductoRepository springDataProductoRepository;
    private final ProductoMapperJpa productoMapperJpa;

    public ProductoReadRepositoryJpaAdapter (SpringDataProductoRepository springDataProductoRepository, ProductoMapperJpa productoMapperJpa){
        this.springDataProductoRepository = springDataProductoRepository;
        this.productoMapperJpa = productoMapperJpa;
    }

    private void validarFiltro(ProductoFilter filter){
        if(filter == null){
            throw new IllegalArgumentException("El filtro no puede ser nulo.");
        }

        if (filter.getPrecioMin() != null && filter.getPrecioMax() != null &&
        filter.getPrecioMin().compareTo(filter.getPrecioMax()) > 0 ){
            throw new IllegalArgumentException("El precio mínimo no puede ser mayor que el máximo.");
        }
    }
    @Override
    public Optional<Producto> findById(Integer id){
        try {
            return springDataProductoRepository.findById(id).map(productoMapperJpa::toDomain);
        }
         catch (Exception e){
            throw new RuntimeException("Error al acceder a la base de datos al buscar producto por id.", e);
         }
    }

    @Override
    public Optional<Producto> findByNombre(String nombre){
        try {
            return  springDataProductoRepository.findByNombre(nombre).map(productoMapperJpa::toDomain);
        } catch (DataAccessException e){
            throw new RuntimeException("Error al acceder a la base de datos al buscar producto por nombre.", e);
        }
    }

    @Override
    public Optional<Producto> findByCodigo(String codigo){
        try {
            return springDataProductoRepository.findByCodigo(codigo).map(productoMapperJpa::toDomain);
        } catch (DataAccessException e){
            throw new RuntimeException("Error al acceder a la base de datos al buscar producto por código.");
        }
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        try {
            return springDataProductoRepository.findAll(pageable)
                    .map(productoMapperJpa::toDomain);
        } catch (DataAccessException e){
            throw new RuntimeException("Error al acceder a la base de datos al buscar productos.", e);
        }

    }

    @Override
    public Page<Producto> findAllConFiltro(ProductoFilter filter, Pageable pageable){
        try {
            validarFiltro(filter);
            Specification<JpaProductoEntity> spec = ProductoSpecificationJpa.filterBy(filter);
            return springDataProductoRepository.findAll(spec, pageable)
                    .map(productoMapperJpa::toDomain);
        } catch (DataAccessException e){
            throw new RuntimeException("Error al acceder a la base de datos al buscar productos con filtro", e);
        }
    }
}
