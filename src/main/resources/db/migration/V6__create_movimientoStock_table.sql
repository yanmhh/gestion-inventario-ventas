CREATE TABLE movimiento_stock (
    movimiento_stock_id SERIAL PRIMARY KEY,

    producto_id INT NOT NULL,
    tipo_movimiento_id INT NOT NULL,
    usuario_id INT NOT NULL,

    cantidad INT NOT NULL,
    fecha TIMESTAMP NOT NULL,
    referencia TEXT,

    creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_movimiento_producto FOREIGN KEY (producto_id)
        REFERENCES producto(producto_id) ON DELETE RESTRICT,

    CONSTRAINT fk_movimiento_tipo FOREIGN KEY (tipo_movimiento_id)
        REFERENCES tipo_movimiento(tipo_movimiento_id) ON DELETE RESTRICT,

    CONSTRAINT fk_movimiento_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario(id) ON DELETE RESTRICT
);
