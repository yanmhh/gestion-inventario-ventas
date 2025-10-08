package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.email.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailTestController {
    private final EmailService emailService;

    public EmailTestController(EmailService emailService) {
        this.emailService = emailService;
    }

    public static class EmailRequest {
        public String to;
        public String subject;
        public String body;
    }

    @PostMapping("/enviar-email")
    public String enviarEmailPrueba(@RequestBody EmailRequest emailRequest) {
        emailService.enviarEmail(emailRequest.to, emailRequest.subject, emailRequest.body);
        return "Correo enviado a " + emailRequest.to;
    }
}
