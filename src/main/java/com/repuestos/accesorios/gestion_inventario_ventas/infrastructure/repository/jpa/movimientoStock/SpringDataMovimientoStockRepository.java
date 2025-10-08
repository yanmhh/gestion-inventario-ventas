package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.movimientoStock;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.movimientoStock.JpaMovimientoStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMovimientoStockRepository extends JpaRepository<JpaMovimientoStockEntity, Integer> {

    List<JpaMovimientoStockEntity> findAllByProductoId(Integer productoId);
}
