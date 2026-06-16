package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.marca;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "marca")
public class JpaMarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marca_id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

}
