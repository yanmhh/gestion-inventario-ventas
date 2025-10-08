-- Tabla Marca
CREATE TABLE marca (
    marca_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla Categoria
CREATE TABLE categoria (
    categoria_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);
-- Tabla Producto
CREATE TABLE producto (
    producto_id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio_venta NUMERIC(10, 2) NOT NULL,
    costo_compra NUMERIC(10,2),
    stock INTEGER NOT NULL DEFAULT 0,
    stock_minimo INTEGER NOT NULL DEFAULT 2,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    imagen_url VARCHAR(500),
    marca_id INTEGER NOT NULL,
    categoria_id INTEGER NOT NULL,
    creado_en TIMESTAMP DEFAULT NOW(),
    actualizado_en TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_marca FOREIGN KEY (marca_id) REFERENCES marca(marca_id),
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(categoria_id)
);

