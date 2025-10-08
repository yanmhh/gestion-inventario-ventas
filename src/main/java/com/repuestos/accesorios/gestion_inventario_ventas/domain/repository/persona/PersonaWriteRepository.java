package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

public interface PersonaWriteRepository extends PersonaFinder{
    Persona save (Persona persona);
    void delete(Persona persona);
}
