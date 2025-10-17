package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaView;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

import java.util.Objects;

public final class CategoriaViewMapper {

    private CategoriaViewMapper() {
    }

    public static CategoriaView toView (Categoria categoria){
        Objects.requireNonNull(categoria, "La categoria no puede ser null.");

        return new CategoriaView(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}
