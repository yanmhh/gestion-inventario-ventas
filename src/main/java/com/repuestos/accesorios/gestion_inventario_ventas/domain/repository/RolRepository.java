package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolRepository {
    Optional<Rol> findById(Integer id);
    List<Rol> findAll();
    Rol save(Rol rol);
    void deleteById(Integer id);
}
