package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarEmail(String to, String subject, String body) {
        System.out.println("Enviando correo a: " + to);
        System.out.println("Asunto: " + subject);
        System.out.println("Mensaje: " + body);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("Correo enviado a: " + to);
        } catch (Exception e) {
            System.err.println("Error enviando correo:");
            e.printStackTrace();
        }
    }
}
