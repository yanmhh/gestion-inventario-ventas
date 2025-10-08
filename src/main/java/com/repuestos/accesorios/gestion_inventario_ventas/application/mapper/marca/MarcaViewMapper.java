package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;

import java.util.Objects;

public class MarcaViewMapper {

    private MarcaViewMapper(){
    }

    public static MarcaDto from(Marca marca){
        Objects.requireNonNull(marca, "Marca no puede ser null.");

        return new MarcaDto(
                marca.getId(),
                marca.getNombre()
        );
    }
}
