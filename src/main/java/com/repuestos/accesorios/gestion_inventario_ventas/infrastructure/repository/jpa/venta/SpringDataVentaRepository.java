package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.venta;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.EstadoVentaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.venta.JpaVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataVentaRepository extends JpaRepository<JpaVentaEntity, Integer> {

    List<JpaVentaEntity> findByEstado(EstadoVentaEntity estado);
    List<JpaVentaEntity> findByClienteId (Integer id);

}

