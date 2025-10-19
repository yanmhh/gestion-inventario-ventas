CREATE TABLE alertas (
    id BIGSERIAL PRIMARY KEY,
    producto_id INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    mensaje TEXT NOT NULL,
    leido BOOLEAN DEFAULT FALSE,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_alerta_producto FOREIGN KEY (producto_id)
    REFERENCES producto (producto_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_alerta_usuario FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE CASCADE
);
