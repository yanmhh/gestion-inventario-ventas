package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;

public interface MarcaWriteRepository extends MarcaFinder {

    Marca save (Marca marca);
    void delete(Marca marca);
}
