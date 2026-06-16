package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Objects;

public class Alerta {
    private final Integer id;
    private final Producto producto;
    private final Usuario usuario;
    private final String mensaje;
    private boolean leido;
    private final LocalDateTime creadoEn;

    public Alerta(Integer id, Producto producto, Usuario usuario, String mensaje, boolean leido, LocalDateTime creadoEn) {
        validar(producto, usuario, mensaje);
        this.id = id;
        this.producto = producto;
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.leido = leido;
        this.creadoEn = Objects.requireNonNullElseGet(creadoEn, LocalDateTime::now);
    }

    public static Alerta crear(Producto producto, Usuario usuario, String mensaje) {
        return new Alerta(null, producto, usuario, mensaje, false, LocalDateTime.now());
    }

    public void marcarComoLeido() {
        this.leido = true;
    }

    private void validar(Producto producto, Usuario usuario, String mensaje) {
        Objects.requireNonNull(producto, "El producto no puede ser nulo");
        Objects.requireNonNull(usuario, "El usuario no puede ser nulo");
        if (mensaje == null || mensaje.isBlank()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío");
        }
    }

    public Integer getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isLeido() {
        return leido;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }
}
