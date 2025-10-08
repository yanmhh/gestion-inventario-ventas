package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.ProductoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.StockInsuficienteException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto.Producto;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.MovimientoStockId;

import java.time.LocalDateTime;

public class MovimientoStock {

    private final MovimientoStockId  id;
    private final Producto producto;
    private final TipoMovimiento tipoMovimiento;
    private final int cantidad;
    private final LocalDateTime fecha;
    private final String referencia;
    private final Usuario usuario;
    private final LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public MovimientoStock(MovimientoStockId  id, Producto producto, TipoMovimiento tipoMovimiento, int cantidad,
                           LocalDateTime fecha, String referencia, Usuario usuario, LocalDateTime creadoEn, LocalDateTime actualizadoEn){
        validar(producto, tipoMovimiento, cantidad, fecha);
        this.id = id;
        this.producto = producto;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fecha = fecha != null ? fecha : LocalDateTime.now();
        this.referencia = referencia;
        this.usuario = usuario;
        this.creadoEn = creadoEn != null ? creadoEn : LocalDateTime.now();
        this.actualizadoEn = actualizadoEn != null ? actualizadoEn : LocalDateTime.now();
    }

    public MovimientoStockId getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public static MovimientoStock crearNuevo(Producto producto, TipoMovimiento tipoMovimiento,
                                             int cantidad, String referencia, Usuario usuario) {
        return new MovimientoStock(
                new MovimientoStockId(0),
                producto,
                tipoMovimiento,
                cantidad,
                LocalDateTime.now(),
                referencia,
                usuario,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void aplicarMovimiento() {
        if (tipoMovimiento.esEntrada()) {
            producto.incrementarStock(cantidad);
        } else if (tipoMovimiento.esSalida()) {
            if (producto.getStock() < cantidad) {
                throw new StockInsuficienteException("Stock insuficiente para realizar la salida." + producto.getNombre());
            }
            producto.decrementarStock(cantidad);
        }
        this.actualizadoEn = LocalDateTime.now();
    }

    private void validarProducto(Producto producto){
        if (producto == null)throw new ProductoNoEncontradoException("Prducto no puede ser null.");
    }

    private  void validarTipoMovimiento(TipoMovimiento tipoMovimiento){
        if (tipoMovimiento == null ) throw new IllegalArgumentException("Tipo de movimiento no puede ser null.");
    }

    private void validarCantidad(int cantidad){
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser mayor a 0.");
    }

    private void validarFecha(LocalDateTime fecha){
        if (fecha == null || fecha.isAfter(LocalDateTime.now())) throw new IllegalArgumentException("Fecha invalida.");
    }

    private void validar(Producto producto, TipoMovimiento tipoMovimiento, int cantidad, LocalDateTime fecha){
        validarProducto(producto);
        validarTipoMovimiento(tipoMovimiento);
        validarCantidad(cantidad);
        validarFecha(fecha);
    }


}
