package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.cliente;

import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.persona.JpaPersonaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
public class JpaClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", nullable = false)
    private JpaPersonaEntity persona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoClienteEntity tipoCliente;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "documento_identidad", nullable = false, unique = true)
    private String documentoIdentidad;

    @Column(name = "ruc_empresa", unique = true)
    private String rucEmpresa;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    public JpaClienteEntity() {
    }

    public Integer getId() {
        return id;
    }

    public JpaPersonaEntity getPersona() {
        return persona;
    }

    public TipoClienteEntity getTipoCliente() {
        return tipoCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPersona(JpaPersonaEntity persona) {
        this.persona = persona;
    }

    public void setTipoCliente(TipoClienteEntity tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }

    @PrePersist
    protected void onCreate() {
        this.creadoEn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEn = LocalDateTime.now();
    }
}
