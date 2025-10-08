package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;

import java.util.List;

public interface ClienteReadRepository extends ClienteFinder {

    List<Cliente> findAll();

    List<Cliente> findByTipoCliente(String tipoClienteNombre);

    boolean existePorDocumentoIdentidad(String documentoIdentidad);

    boolean existePorRuc(String rucEmpresa);

}
