package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {

    Optional<Persona> findById(Integer id);

    Optional<Persona> findByCorreo(String correo);

    boolean existePorCorreo(String correo);
    
    List<Persona> findAll();
    
    Persona save(Persona persona);
    
    void delete(Persona persona);
}
