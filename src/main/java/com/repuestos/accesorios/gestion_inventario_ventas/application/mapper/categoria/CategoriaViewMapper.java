package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

import java.util.Objects;

public class CategoriaViewMapper {

    private CategoriaViewMapper() {
    }

    public static CategoriaDto from (Categoria categoria){
        Objects.requireNonNull(categoria, "La categoria no puede ser null.");

        return new CategoriaDto(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}
