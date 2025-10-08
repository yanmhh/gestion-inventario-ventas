package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;

import java.util.Optional;

public interface MarcaFinder {

    Optional<Marca> findById(Integer id);
    Optional<Marca> findByNombre (String nombre);
}
