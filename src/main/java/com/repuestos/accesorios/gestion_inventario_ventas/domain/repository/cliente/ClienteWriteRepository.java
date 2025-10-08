package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;

public interface ClienteWriteRepository extends ClienteFinder{

    Cliente save(Cliente cliente);

    void delete(Cliente cliente);
}
