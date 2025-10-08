package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {

    private final Integer id;
    private Persona persona;
    private String contrasenia;
    private Rol rol;
    private EstadoUsuario  estado;
    private final LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Usuario(Integer id, Persona persona, String contrasenia, Rol rol, EstadoUsuario  estado, LocalDateTime creadoEn) {

        validarCampos(persona, contrasenia, rol, estado);

        this.id = id;
        this.persona = persona;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.estado = estado;
        this.creadoEn = creadoEn != null ? creadoEn : LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public EstadoUsuario  getEstado() {
        return estado;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void actualizarUsuario(Persona persona, String contrasenia, EstadoUsuario  estado, LocalDateTime actualizadoEn) {
        validarPersona(persona);
        validarContrasenia(contrasenia);
        validarEstado(estado);

        this.persona = persona;
        this.contrasenia = contrasenia;
        this.estado = estado;
        this.actualizadoEn = LocalDateTime.now();
    }

    public boolean estaActivo() {
        return this.estado == EstadoUsuario.ACTIVO;
    }

    public void asegurarActivo() {
        if (this.estado != EstadoUsuario.ACTIVO) {
            throw new UsuarioInvalidoException("El usuario está inactivo. Contacte al administrador.");
        }
    }

    private void validarPersona(Persona persona) {
        validarNoNulo(persona, "La persona no puede ser nula.");
    }

    private void validarContrasenia(String contrasenia) {
        validarNoVacio(contrasenia, "La contraseña no puede estar vacía.");
        if (contrasenia.length() < 8) {
            throw new UsuarioInvalidoException("La contraseña debe tener al menos 8 caracteres.");
        }

        if (!contrasenia.matches(".*[A-Za-z].*") || !contrasenia.matches(".*\\d.*")) {
            throw new UsuarioInvalidoException("La contraseña debe contener al menos una letra y un número.");
        }
    }

    private void validarRol(Rol rol) {
        validarNoNulo(rol, "El rol no puede ser nulo.");
    }

    private void validarEstado(EstadoUsuario estado) {
        validarNoNulo(estado, "El estado del usuario no puede ser nulo.");
    }

    private void validarCampos(Persona persona, String contrasenia, Rol rol, EstadoUsuario estado){
        validarPersona(persona);
        validarContrasenia(contrasenia);
        validarRol(rol);
        validarEstado(estado);

    }

    private void validarNoVacio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new UsuarioInvalidoException(mensaje);
        }
    }

    private void validarNoNulo(Object valor, String mensaje) {
        if (valor == null) {
            throw new UsuarioInvalidoException(mensaje);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", persona=" + persona +
                ", rol=" + rol +
                ", estado=" + estado +
                ", creadoEn=" + creadoEn +
                ", actualizadoEn=" + actualizadoEn +
                '}';
    }
}
