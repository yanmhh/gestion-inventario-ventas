package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;

public interface CategoriaWriteRepository  extends CategoriaFinder{

    Categoria save(Categoria categoria);
    void delete(Categoria categoria);
}
