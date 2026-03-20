package com.repuestos.accesorios.gestion_inventario_ventas.application.service.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta.AlertaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.alerta.AlertaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.event.StockBajoEvent;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.alerta.Alerta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.alerta.AlertaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.notification.EmailNotificationService;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.notification.WebSocketNotificationService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockAlertaService {

    private static final Logger log = LoggerFactory.getLogger(StockAlertaService.class);

    private final AlertaRepository alertaRepository;
    private final UsuarioRepository usuarioRepository;
    private final WebSocketNotificationService webSocketNotificationService;
    private final EmailNotificationService emailNotificationService;

    public StockAlertaService(
            AlertaRepository alertaRepository,
            UsuarioRepository usuarioRepository,
            WebSocketNotificationService webSocketNotificationService,
            EmailNotificationService emailNotificationService) {
        this.alertaRepository = alertaRepository;
        this.usuarioRepository = usuarioRepository;
        this.webSocketNotificationService = webSocketNotificationService;
        this.emailNotificationService = emailNotificationService;
    }

    @Transactional
    public void procesarAlertaStockBajo(StockBajoEvent event) {
        log.info("Procesando alerta de stock bajo para producto: {}", event.getProducto().getNombre());

        // Obtener usuarios administradores o responsables de inventario
        List<Usuario> usuariosResponsables = obtenerUsuariosResponsables();

        for (Usuario usuario : usuariosResponsables) {
            try {
                // Crear y guardar la alerta en la base de datos
                Alerta alerta = Alerta.crear(event.getProducto(), usuario, event.getMensaje());
                alerta = alertaRepository.save(alerta);

                // Convertir a DTO para envío
                AlertaView alertaView = AlertaViewMapper.toView(alerta);

                // Enviar notificación por WebSocket
                webSocketNotificationService.enviarAlertaAUsuario(usuario.getId(), alertaView);
                webSocketNotificationService.enviarAlertaGeneral(alertaView);

                log.info("Alerta enviada por WebSocket al usuario: {}", usuario.getId());

            } catch (Exception e) {
                log.error("Error al crear alerta para usuario: {}", usuario.getId(), e);
            }
        }

        // Enviar email de notificación
        try {
            emailNotificationService.enviarAlertaStockBajo(
                    event.getProducto().getNombre(),
                    event.getProducto().getCodigo().codigo(),
                    event.getStockActual(),
                    event.getStockMinimo()
            );
        } catch (Exception e) {
            log.error("Error al enviar email de alerta", e);
        }

        log.info("Alerta de stock bajo procesada completamente");
    }

    private List<Usuario> obtenerUsuariosResponsables() {
        // Obtener todos los usuarios con rol ADMIN o similar
        // Por ahora retornamos todos los usuarios, pero puedes filtrar por rol
        return usuarioRepository.findAll();
    }
}
