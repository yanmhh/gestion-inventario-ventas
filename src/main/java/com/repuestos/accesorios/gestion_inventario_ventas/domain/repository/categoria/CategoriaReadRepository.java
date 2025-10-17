package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

import java.util.List;

public interface CategoriaReadRepository extends  CategoriaFinder{

    List<Categoria> findAll();
}
