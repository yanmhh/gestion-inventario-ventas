package com.repuestos.accesorios.gestion_inventario_ventas.application.service.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta.AlertaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.alerta.AlertaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.alerta.Alerta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.alerta.AlertaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AlertaCommandService {
    
    private final AlertaRepository alertaRepository;

    public AlertaCommandService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Transactional
    public AlertaView marcarComoLeida(Integer alertaId) {
        Alerta alerta = alertaRepository.findById(alertaId)
                .orElseThrow(() -> new IllegalArgumentException("Alerta no encontrada con ID: " + alertaId));
        
        alerta.marcarComoLeido();
        alerta = alertaRepository.save(alerta);
        
        return AlertaViewMapper.toView(alerta);
    }
}
