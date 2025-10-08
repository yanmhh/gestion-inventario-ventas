package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.cliente.JpaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<JpaClienteEntity, Integer> {

    Optional<JpaClienteEntity> findByDocumentoIdentidad(String documentoIdentidad);
    Optional<JpaClienteEntity> findByRucEmpresa(String rucEmpresa);
}
