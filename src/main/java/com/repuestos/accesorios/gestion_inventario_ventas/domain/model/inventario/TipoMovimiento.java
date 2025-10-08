package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.TipoMovimientoId;

import java.time.LocalDateTime;
import java.util.Objects;

public class TipoMovimiento {

    private final TipoMovimientoId id;
    private final String nombre;
    private final String descripcion;
    private final LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public TipoMovimiento(TipoMovimientoId id, String nombre, String descripcion,
                          LocalDateTime creadoEn, LocalDateTime actualizadoEn) {
        validarNombre(nombre);
        this.id = id;
        this.nombre = nombre.trim();
        this.descripcion = descripcion;
        this.creadoEn = creadoEn != null ? creadoEn : LocalDateTime.now();
        this.actualizadoEn = actualizadoEn != null ? actualizadoEn : LocalDateTime.now();
    }

    public TipoMovimientoId getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public LocalDateTime getCreadoEn() {
        return this.creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return this.actualizadoEn;
    }

    public boolean esEntrada(){
        return "Entrada".equalsIgnoreCase(this.nombre);
    }

    public boolean esSalida() {
        return "SALIDA".equalsIgnoreCase(this.nombre);
    }

    public void actualizarDescripcion(String nuevaDescripcion) {
        if (nuevaDescripcion != null && !nuevaDescripcion.isBlank()) {
            this.actualizadoEn = LocalDateTime.now();
        }
    }

    private void validarNombre(String nombre){
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre de es obligatorio en el tipo de movimiento");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoMovimiento that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
