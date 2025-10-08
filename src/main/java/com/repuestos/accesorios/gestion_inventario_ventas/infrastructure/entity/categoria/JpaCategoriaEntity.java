package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.categoria;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class JpaCategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Integer id;

    @Column( name = "nombre")
    private String nombre;

    public JpaCategoriaEntity(){
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
