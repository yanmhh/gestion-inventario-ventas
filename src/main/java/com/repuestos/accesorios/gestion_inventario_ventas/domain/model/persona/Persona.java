package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ApellidoInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.CorreoInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.NombreInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.TelefonoInvalidoException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Persona {

    private static final String REGEX_NOMBRE = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$";
    private static final String REGEX_CORREO = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String REGEX_TELEFONO = "^\\+?[0-9]{7,15}$";

    private final Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private final LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Persona(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
                   String telefono, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        validarCampos(nombre, apellidoPaterno,apellidoMaterno, correo, telefono);

        this.id = id;
        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void actualizarNombre(String nuevoNombre) {
        validarNombre(nuevoNombre);
        this.nombre = nuevoNombre;
        this.actualizadoEn = LocalDateTime.now();
    }

    public void actualizarApellidos(String nuevoPaterno, String nuevoMaterno) {
        validarApellidos(nuevoPaterno, nuevoMaterno);
        this.apellidoPaterno = nuevoPaterno;
        this.apellidoMaterno = nuevoMaterno;
        this.actualizadoEn = LocalDateTime.now();
    }

    public void actualizarCorreo(String nuevoCorreo) {
        validarCorreo(nuevoCorreo);
        this.correo = nuevoCorreo;
        this.actualizadoEn = LocalDateTime.now();
    }

    public void actualizarTelefono(String nuevoTelefono) {
        validarTelefono(nuevoTelefono);
        this.telefono = nuevoTelefono;
        this.actualizadoEn = LocalDateTime.now();
    }

    private void actualizarFecha() {
        this.actualizadoEn = LocalDateTime.now();
    }

    private void validarCampos(String nombre, String apellidoPaterno, String apellidoMaterno,
                               String correo, String telefono) {
        validarNombre(nombre);
        validarApellidos(apellidoPaterno, apellidoMaterno);
        validarCorreo(correo);
        validarTelefono(telefono);
    }

    private void validarNombre(String nombre) {
        validarNoVacio(nombre,"El nombre es obligatorio");
        if (!nombre.matches(REGEX_NOMBRE)) {
            throw new NombreInvalidoException("El nombre solo puede contener letras y espacios");
        }
    }

    private void validarApellidos(String apellidoPaterno, String apellidoMaterno) {
        validarNoVacio(apellidoPaterno,"El apellido paterno es obligatorio");
        if (!apellidoPaterno.matches(REGEX_NOMBRE)) {
            throw new ApellidoInvalidoException("El apellido paterno solo puede contener letras y espacios");
        }

        validarNoVacio(apellidoMaterno,"El apellido materno es obligatorio");
        if (!apellidoMaterno.matches(REGEX_NOMBRE)) {
            throw new ApellidoInvalidoException("El apellido materno solo puede contener letras y espacios");
        }
    }

    private void validarCorreo(String correo) {
        validarNoVacio(correo,"El correo es obligatorio");
        if (!correo.matches(REGEX_CORREO)) {
            throw new CorreoInvalidoException("El correo no es válido");
        }
    }

    private void validarTelefono(String telefono) {
        validarNoVacio(telefono, "El teléfono es obligatorio");
        if (!telefono.matches(REGEX_TELEFONO)) {
            throw new TelefonoInvalidoException("El teléfono debe contener solo números y tener entre 7 y 15 dígitos");
        }
    }

    private void validarNoVacio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(id, persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", creadoEn=" + creadoEn +
                ", actualizadoEn=" + actualizadoEn +
                '}';
    }

}
