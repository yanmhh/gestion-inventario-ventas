package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public final class CodigoProducto {

    private static final Pattern FORMATO_VALIDO = Pattern.compile("^[A-Z0-9]{3,20}$");
    private final String codigo;

    public CodigoProducto(String codigo) {
        this.codigo = normalizarYValidar(codigo);
    }

    public String codigo() {
        return codigo;
    }

    private String normalizarYValidar(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código de producto no puede estar vacío.");
        }

        String codigoNormalizado = codigo.trim().toUpperCase();

        if (!FORMATO_VALIDO.matcher(codigoNormalizado).matches()) {
            throw new IllegalArgumentException("Formato inválido. Se espera letras y números (3-20 caracteres)");
        }

        return codigoNormalizado ;
    }

    @Override
    public String toString() {
        return codigo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CodigoProducto otro)) return false;
        return Objects.equals(codigo, otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
