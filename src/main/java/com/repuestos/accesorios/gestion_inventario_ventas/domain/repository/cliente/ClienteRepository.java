package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository {


    Optional<Cliente> findById(Integer id);

    Optional<Cliente> findByDocumentoIdentidad(String documentoIdentidad);

    Optional<Cliente> findByPorRuc(String rucEmpresa);


    List<Cliente> findAll();

    List<Cliente> findByTipoCliente(String tipoClienteNombre);

    boolean existePorDocumentoIdentidad(String documentoIdentidad);

    boolean existePorRuc(String rucEmpresa);

    Cliente save(Cliente cliente);

    void delete(Cliente cliente);
}
