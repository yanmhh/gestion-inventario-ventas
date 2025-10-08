package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto.ProductoFilter;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductoReadRepository extends ProductoFinder {
    Page<Producto> findAll(Pageable pageable);
    Page<Producto> findAllConFiltro(ProductoFilter filtro, Pageable pageable);

}
