package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import java.util.List;


public interface MarcaReadRepository extends MarcaFinder {

    List<Marca> findAll();
}
