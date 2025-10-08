package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;

import java.util.Optional;

public interface ClienteFinder {

    Optional<Cliente> findById(Integer id);

    Optional<Cliente> findByDocumentoIdentidad(String documentoIdentidad);

    Optional<Cliente> findByPorRuc(String rucEmpresa);
}
