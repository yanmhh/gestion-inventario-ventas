package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

import java.util.Optional;

public interface CategoriaFinder {

    Optional<Categoria> findById(Integer id);
    Optional<Categoria> findByNombre( String nombre);
}
