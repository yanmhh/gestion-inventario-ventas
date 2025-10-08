package com.repuestos.accesorios.gestion_inventario_ventas.application.service.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.ClienteView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.cliente.RegistroClienteDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.cliente.ClienteMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.cliente.ClienteViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ClienteNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ClienteYaExisteException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.cliente.ClienteWriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClienteCommandService {
    private final ClienteWriteRepository clienteWriteRepository;

    public ClienteCommandService(ClienteWriteRepository clienteWriteRepository) {
        this.clienteWriteRepository = clienteWriteRepository;
    }

    @Transactional
    public ClienteView crearCliente(RegistroClienteDto registroClienteDto) {
        verificarUnicidadDocumentoYRuc(registroClienteDto.getDocumentoIdentidad(), registroClienteDto.getRucEmpresa(), null);

        var nuevoCliente = ClienteMapper.toDomain(registroClienteDto);
        var clienteGuardado = clienteWriteRepository.save(nuevoCliente);

        return ClienteViewMapper.toView(clienteGuardado);
    }

    @Transactional
    public ClienteView actualizarCliente(Integer clienteId, RegistroClienteDto updateDto) {
        Cliente clienteExistente = clienteWriteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con id: " + clienteId));

        verificarUnicidadDocumentoYRuc(updateDto.getDocumentoIdentidad(), updateDto.getRucEmpresa(), clienteId);

        ClienteMapper.mapUpdateData(clienteExistente,updateDto );
        clienteExistente  = clienteWriteRepository.save(clienteExistente);

        return ClienteViewMapper.toView(clienteExistente);
    }

    @Transactional
    public void eliminarCliente(Integer clienteId) {
        Cliente clienteExistente = clienteWriteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con id: " + clienteId));

        clienteWriteRepository.delete(clienteExistente);
    }

    private void verificarUnicidadDocumentoYRuc(String documentoIdentidad, String rucEmpresa, Integer clienteIdExcluido) {
        if (documentoIdentidad != null && !documentoIdentidad.isBlank()) {
            var existeDocumento = clienteWriteRepository.findByDocumentoIdentidad(documentoIdentidad)
                    .filter(c -> !c.getId().equals(clienteIdExcluido))
                    .isPresent();
            if (existeDocumento) {
                throw new ClienteYaExisteException("El documento de identidad ya está registrado: " + documentoIdentidad);
            }
        }

        if (rucEmpresa != null && !rucEmpresa.isBlank()) {
            var existeRuc = clienteWriteRepository.findByPorRuc(rucEmpresa)
                    .filter(c -> !c.getId().equals(clienteIdExcluido))
                    .isPresent();
            if (existeRuc) {
                throw new ClienteYaExisteException("El RUC ya está registrado: " + rucEmpresa);
            }
        }
    }

}
