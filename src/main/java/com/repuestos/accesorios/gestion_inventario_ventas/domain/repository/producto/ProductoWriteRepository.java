package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;

public interface ProductoWriteRepository extends ProductoFinder{
    Producto save(Producto producto);
    void delete(Producto producto);
}
