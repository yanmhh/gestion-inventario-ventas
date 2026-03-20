package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente.ClienteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.cliente.JpaClienteEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.cliente.ClienteMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ClienteRepositoryJpaAdapter extends BaseRepositoryAdapter<Cliente, JpaClienteEntity, Integer>
        implements ClienteRepository {

    private final ClienteMapperJpa clienteMapperJpa;

    public ClienteRepositoryJpaAdapter(SpringDataClienteRepository springDataClienteRepository,
                                       ClienteMapperJpa clienteMapperJpa) {
        super(springDataClienteRepository);
        this.clienteMapperJpa = clienteMapperJpa;
    }

    @Override
    protected Cliente toDomain(JpaClienteEntity entity) {
        return clienteMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaClienteEntity toEntity(Cliente domain) {
        return clienteMapperJpa.toEntity(domain);
    }

    @Override
    public Optional<Cliente> findByDocumentoIdentidad(String documentoIdentidad) {
        return ((SpringDataClienteRepository) repository).findByDocumentoIdentidad(documentoIdentidad)
                .map(this::toDomain);
    }

    @Override
    public Optional<Cliente> findByPorRuc(String rucEmpresa) {
        return ((SpringDataClienteRepository) repository).findByRucEmpresa(rucEmpresa)
                .map(this::toDomain);
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Cliente> findByTipoCliente(String tipoClienteNombre) {
        return repository.findAll().stream()
                .map(this::toDomain)
                .filter(cliente -> cliente.getTipoCliente() != null
                        && cliente.getTipoCliente().name().equals(tipoClienteNombre))
                .toList();
    }

    @Override
    public boolean existePorDocumentoIdentidad(String documentoIdentidad) {
        return ((SpringDataClienteRepository) repository).findByDocumentoIdentidad(documentoIdentidad).isPresent();
    }

    @Override
    public boolean existePorRuc(String rucEmpresa) {
        return ((SpringDataClienteRepository) repository).findByRucEmpresa(rucEmpresa).isPresent();
    }

    @Override
    public Cliente save(Cliente cliente) {
        JpaClienteEntity entity = toEntity(cliente);
        JpaClienteEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Cliente cliente) {
        JpaClienteEntity entity = toEntity(cliente);
        repository.delete(entity);
    }
}
