package com.repuestos.accesorios.gestion_inventario_ventas.application.service.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.ClienteView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.cliente.ClienteViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ClienteNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteQueryService {
    private final ClienteRepository clienteRepository;

    public ClienteQueryService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteView obtenerClientePorId(Integer Id) {
        Cliente cliente = clienteRepository.findById(Id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con id: " + Id));
        return ClienteViewMapper.toView(cliente);
    }

    public ClienteView obtenerPorDocumentoIdentidad(String documentoIdentidad) {
        Cliente cliente = clienteRepository.findByDocumentoIdentidad(documentoIdentidad)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con documento: " + documentoIdentidad));
        return ClienteViewMapper.toView(cliente);
    }

    public ClienteView obtenerPorRuc(String rucEmpresa) {
        Cliente cliente = clienteRepository.findByPorRuc(rucEmpresa)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con RUC: " + rucEmpresa));
        return ClienteViewMapper.toView(cliente);
    }

    public List<ClienteView> obtenerTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteViewMapper::toView)
                .collect(Collectors.toList());
    }

    public List<ClienteView> obtenerPorTipoCliente(String tipoClienteNombre) {
        return clienteRepository.findByTipoCliente(tipoClienteNombre).stream()
                .map(ClienteViewMapper::toView)
                .collect(Collectors.toList());
    }
}
