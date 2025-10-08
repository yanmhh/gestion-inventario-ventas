package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.marca.JpaMarcaEntity;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapperJpa {

    public Marca toDomain(JpaMarcaEntity entity){
        if(entity == null) return null;
        return new Marca(
                entity.getId(),
                entity.getNombre());
    }

    public JpaMarcaEntity toEntity(Marca marca){
        if( marca == null) return  null;
        JpaMarcaEntity entity = new JpaMarcaEntity();
        entity.setId(marca.getId());
        entity.setNombre(marca.getNombre());
        return entity;
    }
}
