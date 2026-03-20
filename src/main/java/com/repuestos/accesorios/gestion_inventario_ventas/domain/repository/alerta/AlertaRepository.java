package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.alerta.Alerta;

import java.util.List;
import java.util.Optional;


public interface AlertaRepository {


    Optional<Alerta> findById(Integer id);


    List<Alerta> findByUsuarioId(Integer usuarioId);

    List<Alerta> findByUsuarioIdAndLeido(Integer usuarioId, boolean leido);

    List<Alerta> findAllNoLeidas();

    Alerta save(Alerta alerta);
}
