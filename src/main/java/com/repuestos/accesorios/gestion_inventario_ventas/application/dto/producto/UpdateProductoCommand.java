package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.producto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class UpdateProductoCommand {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonProperty("precio_venta")
    private BigDecimal precioVenta;

    @NotNull
    @JsonProperty("costo_compra")
    @DecimalMin(value = "0.0", inclusive = false)
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
    @JsonProperty("marca_id")
    private Integer marcaId;

    @NotNull
    @JsonProperty("categoria_id")
    private Integer categoriaId;

    public UpdateProductoCommand() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public BigDecimal getCostoCompra() {
        return this.costoCompra;
    }

    public int getStock() {
        return stock;
    }

    public int getStockMinimo() {
        return this.stockMinimo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getImagenUrl(){
        return this.imagenUrl;
    }

    public Integer getMarcaId(){
        return this.marcaId;
    }

    public Integer getCategoriaId(){
        return  this.categoriaId;
    }

}
