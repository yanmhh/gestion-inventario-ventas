-- Tabla ROL
CREATE TABLE rol (
    rol_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO rol (nombre) VALUES
   ('ADMIN'),
   ('VENDEDOR');

CREATE TYPE estado AS ENUM(
    'ACTIVO',
    'INACTIVO'
);
-- Tabla USUARIO
CREATE TABLE usuario (
   id SERIAL PRIMARY KEY,
   persona_id INT NOT NULL UNIQUE,
   contrasenia VARCHAR(255) NOT NULL,
   rol_id INT NOT NULL,
   estado VARCHAR(20) NOT NULL,
   creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   actualizado_en TIMESTAMP,

    CONSTRAINT fk_usuario_persona FOREIGN KEY (persona_id) REFERENCES persona (id),
    CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol (rol_id)
);


INSERT INTO usuario (persona_id, contrasenia, estado, rol_id)
VALUES (
           1,
           '$2b$12$A27Wz1hb7qaGQP.x8PKctef/EeEdWsJ4g6w4fYLEgKNxF3TMCbEam',
           'ACTIVO',
           1
       );