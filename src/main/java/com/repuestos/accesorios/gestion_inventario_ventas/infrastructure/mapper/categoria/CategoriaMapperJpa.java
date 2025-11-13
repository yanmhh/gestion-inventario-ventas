package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.categoria.JpaCategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapperJpa {

    public Categoria toDomain(JpaCategoriaEntity entity){
        if(entity == null) return null;
        return new Categoria(
                entity.getId(),
                entity.getNombre());
    }

    public JpaCategoriaEntity toEntity(Categoria categoria){
        if( categoria == null) return  null;
        JpaCategoriaEntity entity = new JpaCategoriaEntity();
        entity.setId(categoria.getId());
        entity.setNombre(categoria.getNombre());
        return entity;
    }
}
