package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente.ClienteWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.cliente.ClienteMapperJpa;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteWriteRepositoryJpaAdapter implements ClienteWriteRepository {
    private final SpringDataClienteRepository springDataClienteRepository;
    private final ClienteMapperJpa clienteMapperJpa;

    public ClienteWriteRepositoryJpaAdapter(SpringDataClienteRepository springDataClienteRepository,
                                            ClienteMapperJpa clienteMapperJpa) {
        this.springDataClienteRepository = springDataClienteRepository;
        this.clienteMapperJpa = clienteMapperJpa;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteMapperJpa.toDomain(springDataClienteRepository.save(clienteMapperJpa.toEntity(cliente)));
    }

    @Override
    public void delete(Cliente cliente) {
        springDataClienteRepository.delete(clienteMapperJpa.toEntity(cliente));
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return springDataClienteRepository.findById(id)
                .map(clienteMapperJpa::toDomain);
    }

    @Override
    public Optional<Cliente> findByDocumentoIdentidad(String documentoIdentidad) {
        return springDataClienteRepository.findByDocumentoIdentidad(documentoIdentidad)
                .map(clienteMapperJpa::toDomain);
    }

    @Override
    public Optional<Cliente> findByPorRuc(String rucEmpresa) {
        return springDataClienteRepository.findByRucEmpresa(rucEmpresa)
                .map(clienteMapperJpa::toDomain);
    }
}
