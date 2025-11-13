package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.detalleVenta;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.detalleVenta.JpaDetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataDetalleVentaRepository extends JpaRepository<JpaDetalleVentaEntity, Integer> {

    List<JpaDetalleVentaEntity> findByVentaId(Integer ventaId);
}
