package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.rol;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapperJpa {
    public Rol toDomain(JpaRolEntity entity) {
        if (entity == null) return null;
        return new Rol(
                entity.getId(),
                entity.getNombre());
    }

    public JpaRolEntity toEntity(Rol rol) {
        if (rol == null) return null;
        JpaRolEntity entity = new JpaRolEntity();
        entity.setId(rol.getId());
        entity.setNombre(rol.getNombre());
        return entity;
    }
}
