CREATE TABLE persona (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido_paterno VARCHAR(100) NOT NULL,
    apellido_materno VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL UNIQUE,
    creado_en TIMESTAMP NOT NULL DEFAULT NOW(),
    actualizado_en TIMESTAMP
);

-- Insert de Persona
INSERT INTO persona (nombre, apellido_paterno, apellido_materno, correo, telefono, creado_en, actualizado_en)
VALUES('yan', 'Huaman', 'Huaman','admin@empresa.com', '912345678', now(), now());
