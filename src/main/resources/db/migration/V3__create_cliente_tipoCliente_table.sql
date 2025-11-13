CREATE TYPE tipo_cliente AS ENUM (
    'PERSONA_NATURAL',
    'EMPRESA'
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    persona_id INTEGER NOT NULL UNIQUE,
    tipo_cliente VARCHAR  NOT NULL,
    razon_social VARCHAR(255),
    documento_identidad VARCHAR(20) NOT NULL UNIQUE,
    ruc_empresa VARCHAR(255) UNIQUE,
    creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado_en TIMESTAMP,

    CONSTRAINT fk_persona FOREIGN KEY (persona_id) REFERENCES persona(id)
);

