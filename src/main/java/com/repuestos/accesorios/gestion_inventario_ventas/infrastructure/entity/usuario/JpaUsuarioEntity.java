package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.EstadoUsuario;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.persona.JpaPersonaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.rol.JpaRolEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "usuario")
public class JpaUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private JpaPersonaEntity persona;

    private String contrasenia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id")
    private JpaRolEntity rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoUsuario estado;

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    public JpaUsuarioEntity(){

    }

    public Integer getId() {
        return id;
    }

    public JpaPersonaEntity getPersona() {
        return persona;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public JpaRolEntity getRol() {
        return rol;
    }

    public EstadoUsuario getEstado() {
        return estado;
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

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setRol(JpaRolEntity rol) {
        this.rol = rol;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
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

