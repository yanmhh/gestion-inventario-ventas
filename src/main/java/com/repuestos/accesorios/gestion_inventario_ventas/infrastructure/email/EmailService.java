package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.email;

public interface EmailService {
    void enviarEmail(String to, String subject, String body);
}
