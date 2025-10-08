package com.repuestos.accesorios.gestion_inventario_ventas.application.service.notificacion;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajo;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.AlertaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.alerta.AlertaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.alerta.AlertaRepository;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {
    private final AlertaRepository alertaRepository;

    public AlertaService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public void crearAlerta(StockBajo evento) {
        String mensaje = String.format(
                "El producto \"%s\" tiene un stock actual de %d unidades (mínimo requerido: %d).",
                evento.getNombreProducto(),
                evento.getStockActual(),
                evento.getStockMinimo()
        );

        AlertaDto alerta = new AlertaDto(
        evento.getProductoId(),
        1,
        mensaje
        );
        guardarAlerta(alerta);
    }
    public void guardarAlerta(AlertaDto alertaDto) {
        AlertaEntity alertaEntity = new AlertaEntity(
                alertaDto.getProductoId(),
                alertaDto.getUsuarioId(),
                alertaDto.getMensaje()
        );
        alertaRepository.save(alertaEntity);
    }
}
