package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente.ClienteReadRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.cliente.ClienteMapperJpa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteReadRepositoryJpaAdapter implements ClienteReadRepository {

    private final SpringDataClienteRepository springDataClienteRepository;
    private final ClienteMapperJpa clienteMapperJpa;

    public ClienteReadRepositoryJpaAdapter(SpringDataClienteRepository springDataClienteRepository,
                                           ClienteMapperJpa clienteMapperJpa) {
        this.springDataClienteRepository = springDataClienteRepository;
        this.clienteMapperJpa = clienteMapperJpa;
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

    @Override
    public List<Cliente> findAll() {
        return springDataClienteRepository.findAll().stream()
                .map(clienteMapperJpa::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> findByTipoCliente(String tipoClienteNombre) {
        return springDataClienteRepository.findAll().stream()
                .map(clienteMapperJpa::toDomain)
                .filter(cliente -> cliente.getTipoCliente() != null
                        && cliente.getTipoCliente().name().equals(tipoClienteNombre))
                .toList();
    }

    @Override
    public boolean existePorDocumentoIdentidad(String documentoIdentidad) {
        return springDataClienteRepository.findByDocumentoIdentidad(documentoIdentidad).isPresent();
    }

    @Override
    public boolean existePorRuc(String rucEmpresa) {
        return springDataClienteRepository.findByRucEmpresa(rucEmpresa).isPresent();
    }
}
