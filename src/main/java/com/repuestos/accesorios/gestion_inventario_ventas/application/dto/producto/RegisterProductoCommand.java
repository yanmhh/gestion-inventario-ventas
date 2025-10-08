package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class RegisterProductoCommand {

    @Size(max = 100)
    @NotBlank
    private String nombre;

    @NotBlank
    @Size(max = 250)
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonProperty("precio_venta")
    private BigDecimal precioVenta;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonProperty("costo_compra")
    private BigDecimal costoCompra;

    @Min(0)
    private int stock;

    @JsonProperty("stock_minimo")
    private int stockMinimo;

    @NotBlank
    @Size(max = 30)
    private String codigo;

    @JsonProperty("imagen_url")
    private String imagenUrl;

    @NotNull
    @JsonProperty("marca")
    private MarcaDto marca;

    @NotNull
    @JsonProperty("categoria")
    private CategoriaDto categoria;

    public RegisterProductoCommand(){
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

    public String getCodigo() {
        return this.codigo;
    }

    public String getImagenUrl(){
        return this.imagenUrl;
    }

    public MarcaDto getMarca(){
        return this.marca;
    }

    public CategoriaDto getCategoria(){
        return  this.categoria;
    }
}
