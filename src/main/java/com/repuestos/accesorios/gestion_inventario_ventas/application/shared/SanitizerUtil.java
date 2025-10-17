package com.repuestos.accesorios.gestion_inventario_ventas.application.shared;

import org.apache.commons.text.StringEscapeUtils;

public class SanitizerUtil {
    public static String sanitize(String input) {
        if (input == null) return null;
        return StringEscapeUtils.escapeHtml4(input.trim());
    }
}
