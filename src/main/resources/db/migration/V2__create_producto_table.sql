--Tabla Producto
CREATE TABLE producto (
     producto_id SERIAL PRIMARY KEY,
     nombre VARCHAR(255),
     descripcion TEXT,
     precio NUMERIC(10, 2),
     stock INTEGER,
     codigo VARCHAR(50) NOT NULL UNIQUE,
     creado_en TIMESTAMP,
     actualizado_en TIMESTAMP
);