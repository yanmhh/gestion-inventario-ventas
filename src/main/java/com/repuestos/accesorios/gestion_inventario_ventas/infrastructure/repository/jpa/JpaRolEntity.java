package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class JpaRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;


    @Column(name = "nombre")
    private String nombre;

    public JpaRolEntity(){

    }

    public Integer getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
