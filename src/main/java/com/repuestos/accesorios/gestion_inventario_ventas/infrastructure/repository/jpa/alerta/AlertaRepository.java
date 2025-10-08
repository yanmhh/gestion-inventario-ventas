package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.alerta.AlertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<AlertaEntity, Long> {

}


