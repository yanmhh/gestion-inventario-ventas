package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.rol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rol")
public class JpaRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;


    @Column(name = "nombre")
    private String nombre;

}
