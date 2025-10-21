-- Tabla ROL
CREATE TABLE rol (
    rol_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
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
