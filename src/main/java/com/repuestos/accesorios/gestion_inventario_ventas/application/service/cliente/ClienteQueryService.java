package com.repuestos.accesorios.gestion_inventario_ventas.application.service.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.ClienteView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.cliente.ClienteViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ClienteNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente.ClienteReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteQueryService {
    private final ClienteReadRepository clienteReadRepository;

    public ClienteQueryService(ClienteReadRepository clienteReadRepository) {
        this.clienteReadRepository = clienteReadRepository;
    }

    public ClienteView obtenerClientePorId(Integer Id) {
        Cliente cliente = clienteReadRepository.findById(Id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con id: " + Id));
        return ClienteViewMapper.toView(cliente);
    }

    public ClienteView obtenerPorDocumentoIdentidad(String documentoIdentidad) {
        Cliente cliente = clienteReadRepository.findByDocumentoIdentidad(documentoIdentidad)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con documento: " + documentoIdentidad));
        return ClienteViewMapper.toView(cliente);
    }

    public ClienteView obtenerPorRuc(String rucEmpresa) {
        Cliente cliente = clienteReadRepository.findByPorRuc(rucEmpresa)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con RUC: " + rucEmpresa));
        return ClienteViewMapper.toView(cliente);
    }

    public List<ClienteView> obtenerTodos() {
        return clienteReadRepository.findAll().stream()
                .map(ClienteViewMapper::toView)
                .collect(Collectors.toList());
    }

    public List<ClienteView> obtenerPorTipoCliente(String tipoClienteNombre) {
        return clienteReadRepository.findByTipoCliente(tipoClienteNombre).stream()
                .map(ClienteViewMapper::toView)
                .collect(Collectors.toList());
    }
}
