package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoFilter;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto.JpaProductoEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductoSpecificationJpa {
    public static Specification<JpaProductoEntity> filterBy(ProductoFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getNombre() != null && !filter.getNombre().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + filter.getNombre().toLowerCase() + "%"
                ));
            }

            if (filter.getCodigo() != null && !filter.getCodigo().isBlank()) {
                predicates.add(criteriaBuilder.equal(
                        root.get("codigo"),
                        filter.getCodigo()
                ));
            }

            if (filter.getPrecioMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("precioVenta"),
                        filter.getPrecioMin()
                ));
            }

            if (filter.getPrecioMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("precioVenta"),
                        filter.getPrecioMax()
                ));
            }

            if (filter.getStockMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("stock"),
                        filter.getStockMin()
                ));
            }

            if (filter.getStockMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("stock"),
                        filter.getStockMax()
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
