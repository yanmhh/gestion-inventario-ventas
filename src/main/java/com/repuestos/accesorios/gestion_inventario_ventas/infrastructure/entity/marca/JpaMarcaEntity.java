package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.marca;

import jakarta.persistence.*;

@Entity
@Table(name = "marca")
public class JpaMarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marca_id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public JpaMarcaEntity(){
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
