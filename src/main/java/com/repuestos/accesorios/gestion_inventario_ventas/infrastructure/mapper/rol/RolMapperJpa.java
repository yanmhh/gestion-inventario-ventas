package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.rol;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.rol.JpaRolEntity;
import org.springframework.stereotype.Component;

@Component
public class RolMapperJpa {
    public  Rol toDomain(JpaRolEntity jpaRolEntity) {
        if (jpaRolEntity == null) return null;
        return new Rol(
                jpaRolEntity.getId(),
                jpaRolEntity.getNombre());
    }

    public JpaRolEntity toEntity(Rol rol) {
        if (rol == null) return null;
        JpaRolEntity jpaRolEntity = new JpaRolEntity();
        jpaRolEntity.setId(rol.getId());
        jpaRolEntity.setNombre(rol.getNombre());
        return jpaRolEntity;
    }
}
