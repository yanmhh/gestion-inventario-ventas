package com.repuestos.accesorios.gestion_inventario_ventas.application.service.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta.AlertaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.alerta.AlertaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.alerta.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaQueryService {
    
    private final AlertaRepository alertaRepository;

    public AlertaQueryService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public List<AlertaView> obtenerAlertasPorUsuario(Integer usuarioId) {
        return alertaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(AlertaViewMapper::toView)
                .collect(Collectors.toList());
    }

    public List<AlertaView> obtenerAlertasNoLeidasPorUsuario(Integer usuarioId) {
        return alertaRepository.findByUsuarioIdAndLeido(usuarioId, false)
                .stream()
                .map(AlertaViewMapper::toView)
                .collect(Collectors.toList());
    }

    public List<AlertaView> obtenerTodasLasAlertasNoLeidas() {
        return alertaRepository.findAllNoLeidas()
                .stream()
                .map(AlertaViewMapper::toView)
                .collect(Collectors.toList());
    }
}
