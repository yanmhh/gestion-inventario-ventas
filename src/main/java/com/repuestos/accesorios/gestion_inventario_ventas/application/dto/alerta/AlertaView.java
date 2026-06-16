package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.alerta;

public class AlertaView {
    private Integer id;
    private Integer productoId;
    private String productoNombre;
    private String productoCodigo;
    private Integer usuarioId;
    private String usuarioNombre;
    private String mensaje;
    private boolean leido;
    private String creadoEn;

    public AlertaView() {
    }

    public AlertaView(Integer id, Integer productoId, String productoNombre, String productoCodigo,
                      Integer usuarioId, String usuarioNombre, String mensaje, boolean leido, String creadoEn) {
        this.id = id;
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.productoCodigo = productoCodigo;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.mensaje = mensaje;
        this.leido = leido;
        this.creadoEn = creadoEn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoCodigo() {
        return productoCodigo;
    }

    public void setProductoCodigo(String productoCodigo) {
        this.productoCodigo = productoCodigo;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public String getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(String creadoEn) {
        this.creadoEn = creadoEn;
    }
}
