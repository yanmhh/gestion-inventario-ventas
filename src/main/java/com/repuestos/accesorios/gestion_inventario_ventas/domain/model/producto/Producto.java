package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.NombreProductoInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.PrecioInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.StockInsuficienteException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.StockInvalidoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.vo.CodigoProducto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Producto {
    private final Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private BigDecimal costoCompra;
    private int stock;
    private int stockMinimo;
    private CodigoProducto codigo;
    private String imagenUrl;
    private Marca marca;
    private Categoria categoria;
    private final LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Producto(Integer id, String nombre, String descripcion, BigDecimal precioVenta,BigDecimal costoCompra,
                    int stock, int stockMinimo, CodigoProducto codigo, String imagenUrl,Marca marca,Categoria categoria,
                    LocalDateTime creadoEn) {
        validar (nombre, precioVenta, costoCompra, stock, stockMinimo);
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.costoCompra = costoCompra;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.codigo = codigo;
        this.imagenUrl = imagenUrl;
        this.marca = marca;
        this.categoria = categoria;
        this.creadoEn = LocalDateTime.now();
    }

    public void actualizarProducto(String nombre, String descripcion, BigDecimal precioVenta, BigDecimal costoCompra,
                                   int stock, int stockMinimo, CodigoProducto codigo ,String imagenUrl, Marca marca, Categoria categoria,
                                   LocalDateTime actualizadoEn){
        validar (nombre, precioVenta, costoCompra, stock, stockMinimo);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.costoCompra = costoCompra;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.codigo = codigo;
        this.imagenUrl = imagenUrl;
        this.marca = marca;
        this.categoria = categoria;
        this.actualizadoEn = LocalDateTime.now();
    }

    public void incrementarStock(int cantidad) {
        if (cantidad <= 0) throw new StockInvalidoException("La cantidad a ingresar debe ser mayor a 0.");
        this.stock += cantidad;
        this.actualizadoEn = LocalDateTime.now();
    }

    public void decrementarStock(int cantidad) {
        if (cantidad <= 0) throw new StockInvalidoException("La cantidad a reducir debe ser mayor a 0.");
        if (cantidad > stock) throw new StockInsuficienteException("Stock insuficiente para realizar la operación.");
        this.stock -= cantidad;
        this.actualizadoEn = LocalDateTime.now();
    }

    public boolean stockBajo(){
        return this.stock <= this.stockMinimo;
    }

    private void validar(String nombre, BigDecimal precioVenta, BigDecimal costoCompra, int stock,int stockMinimo){
        validarNombre(nombre);
        validarPrecioVenta(precioVenta);
        validarCostoCompra(costoCompra);
        validarStock(stock);
        validarStockMinimo(stockMinimo);
    }

    private void validarNombre(String nombre){
        if (nombre == null || nombre.isBlank()){
            throw new NombreProductoInvalidoException("El nombre no puede estar vacío.");
        }
    }

    private void validarPrecioVenta(BigDecimal precioVenta){
        Objects.requireNonNull(precioVenta, "El precio de venta no puede ser nulo.");
        if (precioVenta.compareTo(BigDecimal.ZERO) < 0) {
            throw new PrecioInvalidoException("El precio de venta no puede ser negativo.");
        }
    }

    private void validarCostoCompra(BigDecimal costoCompra){
        Objects.requireNonNull(costoCompra, "El costo de compra no puede ser nulo.");
        if (costoCompra.compareTo(BigDecimal.ZERO) < 0) {
            throw new PrecioInvalidoException("El costo de compra no puede ser negativo.");
        }
    }

    private void validarStock(int stock){
        if (stock < 0){
            throw new StockInvalidoException("El stock no puede ser negativo.");
        }
    }

    private void validarStockMinimo(int stockMinimo){
        if (stockMinimo < 0){
            throw new StockInvalidoException("El stock no puede ser negativo.");
        }
        if (stockMinimo > stock) {
            throw new StockInvalidoException("El stock mínimo no puede ser mayor al stock inicial.");
        }
    }


    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public BigDecimal getPrecioVenta() {
        return this.precioVenta;
    }

    public BigDecimal getCostoCompra() {
        return this.costoCompra;
    }

    public int getStock() {
        return this.stock;
    }

    public int getStockMinimo() {
        return this.stockMinimo;
    }

    public CodigoProducto getCodigo() {
        return codigo;
    }

    public String getImagenUrl(){
        return this.imagenUrl;
    }

    public Marca getMarca(){
        return this.marca;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }

    public LocalDateTime getCreadoEn() {
        return this.creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return this.actualizadoEn;
    }
}
