package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaReadRepository extends  CategoriaFinder{

    Page<Categoria> findAll(Pageable pageable);
}
