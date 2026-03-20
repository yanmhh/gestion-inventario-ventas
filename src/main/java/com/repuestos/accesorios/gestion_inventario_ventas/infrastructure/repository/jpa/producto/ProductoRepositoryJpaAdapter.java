package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoFilter;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto.ProductoRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.producto.ProductoMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductoRepositoryJpaAdapter extends BaseRepositoryAdapter<Producto, JpaProductoEntity, Integer>
        implements ProductoRepository {

    private final ProductoMapperJpa productoMapperJpa;

    public ProductoRepositoryJpaAdapter(SpringDataProductoRepository springDataProductoRepository,
                                        ProductoMapperJpa productoMapperJpa) {
        super(springDataProductoRepository);
        this.productoMapperJpa = productoMapperJpa;
    }

    @Override
    protected Producto toDomain(JpaProductoEntity entity) {
        return productoMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaProductoEntity toEntity(Producto domain) {
        return productoMapperJpa.toEntity(domain);
    }


    @Override
    public Optional<Producto> findByNombre(String nombre) {
        try {
            return ((SpringDataProductoRepository) repository).findByNombre(nombre)
                    .map(this::toDomain);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos al buscar producto por nombre.", e);
        }
    }

    @Override
    public Optional<Producto> findByCodigo(String codigo) {
        try {
            return ((SpringDataProductoRepository) repository).findByCodigo(codigo)
                    .map(this::toDomain);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos al buscar producto por código.", e);
        }
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        try {
            return ((SpringDataProductoRepository) repository).findAll(pageable)
                    .map(this::toDomain);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos al buscar productos.", e);
        }
    }

    @Override
    public Page<Producto> findAllConFiltro(ProductoFilter filter, Pageable pageable) {
        try {
            validarFiltro(filter);
            Specification<JpaProductoEntity> spec = ProductoSpecificationJpa.filterBy(filter);
            return ((SpringDataProductoRepository) repository).findAll(spec, pageable)
                    .map(this::toDomain);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos al buscar productos con filtro", e);
        }
    }

    @Override
    public Producto save(Producto producto) {
        JpaProductoEntity entity = toEntity(producto);
        JpaProductoEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Producto producto) {
        JpaProductoEntity entity = toEntity(producto);
        repository.delete(entity);
    }

    private void validarFiltro(ProductoFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("El filtro no puede ser nulo.");
        }

        if (filter.getPrecioMin() != null && filter.getPrecioMax() != null &&
                filter.getPrecioMin().compareTo(filter.getPrecioMax()) > 0) {
            throw new IllegalArgumentException("El precio mínimo no puede ser mayor que el máximo.");
        }
    }
}
