package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;

import java.util.List;
import java.util.Optional;


public interface MarcaRepository {
    
    Optional<Marca> findById(Integer id);
    Optional<Marca> findByNombre(String nombre);
    List<Marca> findAll();
    
    Marca save(Marca marca);
    void delete(Marca marca);
}
