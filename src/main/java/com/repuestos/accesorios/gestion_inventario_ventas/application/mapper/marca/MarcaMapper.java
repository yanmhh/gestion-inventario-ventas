package com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;

public class MarcaMapper {

    private MarcaMapper(){
    }

    public static Marca from (MarcaDto registerMarcaDto){
        return  new Marca(
                null,
                registerMarcaDto.getNombre()
        );
    }
}
