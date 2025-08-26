-- Tabla ROL
CREATE TABLE rol (
    rol_id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Tabla USUARIO
CREATE TABLE usuario (
    usuario_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    apellido_paterno VARCHAR(255) NOT NULL,
    apellido_materno VARCHAR(255) NOT NULL,
    email VARCHAR(150) UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    rol_id INT NOT NULL,
    creado_en TIMESTAMP,
    actualizado_en TIMESTAMP,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol (rol_id)
);

-- Datos iniciales
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('VENDEDOR');

INSERT INTO usuario (nombre, apellido_paterno, apellido_materno, email, password, telefono, estado, rol_id)
VALUES
('admin', 'yan', 'huaman','admin@empresa.com','$2a$10$Jswxlki9DMnF25W1Z/xC0OTVVf4bGFNO3XMqMQPNkbC97dxSg2h1i', '912345678',true, 1);