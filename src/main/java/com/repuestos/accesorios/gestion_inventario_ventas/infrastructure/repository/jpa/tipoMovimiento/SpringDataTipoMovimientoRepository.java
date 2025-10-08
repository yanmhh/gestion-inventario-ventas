package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.tipoMovimiento.JpaTipoMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataTipoMovimientoRepository extends JpaRepository<JpaTipoMovimientoEntity, Integer> {

    Optional<JpaTipoMovimientoEntity> findById (Integer id);
    Optional<JpaTipoMovimientoEntity> findByNombre(String nombre);
}
