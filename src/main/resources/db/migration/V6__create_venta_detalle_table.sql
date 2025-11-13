CREATE TABLE venta (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha_venta TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR NOT NULL,
    tipo_documento VARCHAR(50),
    total NUMERIC(10,2) NOT NULL,
    usuario_id INT NOT NULL,
    observaciones TEXT,
    creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado_en TIMESTAMP,

    CONSTRAINT fk_venta_cliente FOREIGN KEY (cliente_id)
        REFERENCES cliente(id) ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT fk_venta_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT
);


CREATE TABLE detalle_venta (
    id SERIAL PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL ,
    precio_unitario NUMERIC(15,2) NOT NULL,

    CONSTRAINT fk_detalle_venta FOREIGN KEY (venta_id)
        REFERENCES venta (id) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT fk_detalle_producto FOREIGN KEY (producto_id)
        REFERENCES producto (producto_id) ON UPDATE CASCADE ON DELETE RESTRICT
);