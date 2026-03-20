package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:noreply@inventario.com}")
    private String fromEmail;

    @Value("${app.alertas.email-destino:admin@inventario.com}")
    private String destinatarioDefault;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void enviarAlertaStockBajo(String productoNombre, String productoCodigo, 
                                      int stockActual, int stockMinimo) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom(fromEmail);
            mensaje.setTo(destinatarioDefault);
            mensaje.setSubject("⚠️ ALERTA: Stock Bajo - " + productoNombre);
            
            String contenido = String.format(
                    "ALERTA DE STOCK BAJO\n\n" +
                    "Producto: %s\n" +
                    "Código: %s\n" +
                    "Stock Actual: %d unidades\n" +
                    "Stock Mínimo: %d unidades\n\n" +
                    "Es necesario realizar un pedido de reabastecimiento.\n\n" +
                    "---\n" +
                    "Sistema de Gestión de Inventario",
                    productoNombre, productoCodigo, stockActual, stockMinimo
            );
            
            mensaje.setText(contenido);
            
            mailSender.send(mensaje);
            log.info("Email de alerta enviado para producto: {}", productoNombre);
            
        } catch (Exception e) {
            log.error("Error al enviar email de alerta para producto: {}", productoNombre, e);
        }
    }

    @Async
    public void enviarAlertaAUsuarioEspecifico(String email, String productoNombre, 
                                               String productoCodigo, int stockActual, int stockMinimo) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setFrom(fromEmail);
            mensaje.setTo(email);
            mensaje.setSubject("⚠️ ALERTA: Stock Bajo - " + productoNombre);
            
            String contenido = String.format(
                    "ALERTA DE STOCK BAJO\n\n" +
                    "Estimado usuario,\n\n" +
                    "Le informamos que el siguiente producto tiene stock bajo:\n\n" +
                    "Producto: %s\n" +
                    "Código: %s\n" +
                    "Stock Actual: %d unidades\n" +
                    "Stock Mínimo: %d unidades\n\n" +
                    "Por favor, tome las medidas necesarias.\n\n" +
                    "---\n" +
                    "Sistema de Gestión de Inventario",
                    productoNombre, productoCodigo, stockActual, stockMinimo
            );
            
            mensaje.setText(contenido);
            
            mailSender.send(mensaje);
            log.info("Email de alerta enviado a usuario: {}", email);
            
        } catch (Exception e) {
            log.error("Error al enviar email de alerta a: {}", email, e);
        }
    }
}
