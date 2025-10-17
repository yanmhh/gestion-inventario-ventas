package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

public final class CategoriaMapper {

    private CategoriaMapper(){
    }

    public static Categoria toDomain(CategoriaDto dto){
        if (dto == null) return null;

        return new Categoria(
                null,
                dto.getNombre()
        );
    }

    public static void mapUpdateData(CategoriaDto actualizar, Categoria categoria){
        categoria.actualizarCategoria(
                actualizar.getNombre()
        );
    }
}
