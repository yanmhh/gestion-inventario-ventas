package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.alerta.JpaAlertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataAlertaRepository extends JpaRepository<JpaAlertaEntity, Integer> {
    List<JpaAlertaEntity> findByUsuarioId(Integer usuarioId);
    List<JpaAlertaEntity> findByUsuarioIdAndLeido(Integer usuarioId, boolean leido);
    List<JpaAlertaEntity> findByLeido(boolean leido);
}
