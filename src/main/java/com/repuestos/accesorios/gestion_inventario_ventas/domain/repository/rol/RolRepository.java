package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.rol;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;

import java.util.List;
import java.util.Optional;

public interface RolRepository {
    Optional<Rol> findById(Integer id);
    Optional<Rol> findByNombre(String nombre);
    List<Rol> findAll();
    Rol save(Rol rol);
    void deleteById(Integer id);
}
