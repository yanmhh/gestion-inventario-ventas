package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.producto;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.categoria.JpaCategoriaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.marca.JpaMarcaEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "producto")
public class JpaProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @Column(name = "costo_compra")
    private BigDecimal costoCompra;

    @Column(name = "stock")
    private int stock;

    @Column(name = "stock_minimo")
    private int stockMinimo;

    @Column(name = "codigo", nullable = false , unique = true, length = 50)
    private String codigo;

    @Column (name = "imagen_url", length = 500)
    private String imagenUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "marca_id")
    private JpaMarcaEntity marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "categoria_id")
    private JpaCategoriaEntity categoria;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    public JpaProductoEntity(){

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(BigDecimal costoCompra) {
        this.costoCompra = costoCompra;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return this.stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagenUrl(){
        return this.imagenUrl;
    }

    public void setImagenUrl(String imagenUrl){
        this.imagenUrl = imagenUrl;
    }

    public JpaMarcaEntity getMarca() {
        return this.marca;
    }

    public void setMarca(JpaMarcaEntity marca) {
        this.marca = marca;
    }

    public JpaCategoriaEntity getCategoria() {
        return this.categoria;
    }

    public void setCategoria(JpaCategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getCreadoEn() {
        return this.creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return this.actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }

    @PrePersist
    protected void onCreate(){
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = LocalDateTime.now();
    }
    @PreUpdate
    protected  void onUpdate(){
        this.actualizadoEn = LocalDateTime.now();
    }
}
