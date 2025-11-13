package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente.Cliente;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.cliente.JpaClienteEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.persona.PersonaMapperJpa;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperJpa {

    private final PersonaMapperJpa personaMapperJpa;
    private final TipoClienteMapperJpa tipoClienteMapperJpa;

    public ClienteMapperJpa(PersonaMapperJpa personaMapperJpa, TipoClienteMapperJpa tipoClienteMapperJpa) {
        this.personaMapperJpa = personaMapperJpa;
        this.tipoClienteMapperJpa = tipoClienteMapperJpa;
    }

    public Cliente toDomain(JpaClienteEntity jpaClienteEntity) {
        if (jpaClienteEntity == null) {
            return null;
        }
        return new Cliente(
                jpaClienteEntity.getId(),
                personaMapperJpa.toDomain(jpaClienteEntity.getPersona()),
                tipoClienteMapperJpa.toDomain(jpaClienteEntity.getTipoCliente()),
                jpaClienteEntity.getRazonSocial(),
                jpaClienteEntity.getDocumentoIdentidad(),
                jpaClienteEntity.getRucEmpresa(),
                jpaClienteEntity.getCreadoEn()
        );
    }

    public JpaClienteEntity toEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        JpaClienteEntity jpaClienteEntity = new JpaClienteEntity();
        jpaClienteEntity.setId(cliente.getId());
        jpaClienteEntity.setPersona(personaMapperJpa.toEntity(cliente.getPersona()));
        jpaClienteEntity.setTipoCliente(tipoClienteMapperJpa.toEntity(cliente.getTipoCliente()));
        jpaClienteEntity.setRazonSocial(cliente.getRazonSocial());
        jpaClienteEntity.setDocumentoIdentidad(cliente.getDocumentoIdentidad());
        jpaClienteEntity.setRucEmpresa(cliente.getRucEmpresa());
        jpaClienteEntity.setCreadoEn(cliente.getCreadoEn());
        jpaClienteEntity.setActualizadoEn(cliente.getActualizadoEn());
        return jpaClienteEntity;
    }
}
