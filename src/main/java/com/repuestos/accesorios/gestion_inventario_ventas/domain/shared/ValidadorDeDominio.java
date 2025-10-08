package com.repuestos.accesorios.gestion_inventario_ventas.domain.shared;

public class ValidadorDeDominio {

    public static void validarNoVacio(String valor, String mensaje, RuntimeException exception) {
        if (valor == null || valor.isBlank()) {
            throw exception;
        }
    }

    public static void validarRegex(String valor, String regex, RuntimeException exception) {
        if (!valor.matches(regex)) {
            throw exception;
        }
    }

    public static void validarLongitud(String valor, int min, int max, RuntimeException exception) {
        if (valor.length() < min || valor.length() > max) {
            throw exception;
        }
    }

    public static void validarNoNulo(Object objeto, RuntimeException exception) {
        if (objeto == null) {
            throw exception;
        }
    }
}
