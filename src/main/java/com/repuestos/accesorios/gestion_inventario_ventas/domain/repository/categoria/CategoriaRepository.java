package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

import java.util.List;
import java.util.Optional;


public interface CategoriaRepository {
    
    Optional<Categoria> findById(Integer id);
    Optional<Categoria> findByNombre(String nombre);
    List<Categoria> findAll();
    
    Categoria save(Categoria categoria);
    void delete(Categoria categoria);
}
