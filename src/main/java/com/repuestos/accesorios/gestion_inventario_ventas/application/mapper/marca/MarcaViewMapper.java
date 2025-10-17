package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaView;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;

import java.util.Objects;

public final class MarcaViewMapper {

    private MarcaViewMapper(){
    }

    public static MarcaView toView(Marca marca){
        Objects.requireNonNull(marca, "Marca no puede ser null.");

        return new MarcaView(
                marca.getId(),
                marca.getNombre()
        );
    }
}
