package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MarcaReadRepository extends MarcaFinder {

    Page<Marca> findAll(Pageable pageable);
}
