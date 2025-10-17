package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;

public final class MarcaMapper {

    private MarcaMapper(){
    }

    public static Marca toDomain (MarcaDto registerMarcaDto){
        return new Marca(
                null,
                registerMarcaDto.getNombre()
        );
    }

    public static void mapUpdateData(MarcaDto actualizar, Marca marca){
        marca.actualizarMarca(
                actualizar.getNombre()
        );
    }
}
