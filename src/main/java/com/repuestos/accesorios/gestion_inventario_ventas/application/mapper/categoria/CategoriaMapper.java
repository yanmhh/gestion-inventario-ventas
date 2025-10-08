package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

public class CategoriaMapper {

    private CategoriaMapper(){
    }

    public static Categoria from(CategoriaDto registerCategoriaDto){
        return new Categoria(
                null,
                registerCategoriaDto.getNombre()
        );
    }
}
