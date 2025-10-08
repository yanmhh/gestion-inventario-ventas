package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.util.List;

public interface PersonaReadRepository extends PersonaFinder{
    List<Persona> findAll();
}
