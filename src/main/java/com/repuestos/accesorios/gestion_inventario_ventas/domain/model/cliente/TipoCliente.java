package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ClienteInvalidoException;

public enum TipoCliente {

    PERSONA_NATURAL{
        @Override
        public void validar(String razonSocial, String documentoIdentidad, String rucEmpresa) {
            if (rucEmpresa != null && !rucEmpresa.isBlank()) {
                throw new ClienteInvalidoException("Una persona natural no debe tener RUC.");
            }
            validarNoVacio(documentoIdentidad, "La persona natural debe tener documento de identidad.");
        }
    },
    EMPRESA {
        @Override
        public void validar(String razonSocial, String documentoIdentidad, String rucEmpresa) {
            validarNoVacio(rucEmpresa, "Una empresa debe tener RUC.");
            validarFormatoRuc(rucEmpresa);
            validarNoVacio(razonSocial, "Una empresa debe tener razón social.");
        }
    },
    MAYORISTA {
        @Override
        public void validar(String razonSocial, String documentoIdentidad, String rucEmpresa) {
            validarNoVacio(rucEmpresa, "Un mayorista debe tener RUC.");
            validarFormatoRuc(rucEmpresa);
            validarNoVacio(documentoIdentidad, "Un mayorista debe tener documento de identidad.");
        }
    },
    MINORISTA{
        @Override
        public void validar(String razonSocial, String documentoIdentidad, String rucEmpresa) {
            validarNoVacio(documentoIdentidad, "Un minorista debe tener documento de identidad.");
        }
    };

    protected static void validarFormatoRuc(String ruc) {
        if (!ruc.matches("^\\d{11}$")) {
            throw new ClienteInvalidoException("El RUC debe contener exactamente 11 dígitos numéricos.");
        }
    }

    public abstract void validar(String razonSocial, String documentoIdentidad, String rucEmpresa);

    protected static void validarNoVacio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new ClienteInvalidoException(mensaje);
        }
    }

    public static void validarTipo(TipoCliente tipoCliente, String razonSocial, String documentoIdentidad, String rucEmpresa) {
        if (tipoCliente == null) {
            throw new ClienteInvalidoException("El tipo de cliente es obligatorio.");
        }
        tipoCliente.validar(razonSocial, documentoIdentidad, rucEmpresa);
    }
}
