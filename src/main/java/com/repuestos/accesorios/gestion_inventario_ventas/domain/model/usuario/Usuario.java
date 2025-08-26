package com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;

import java.time.LocalDateTime;
public class Usuario {

    private final Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String password;
    private String telefono;
    private Rol rol;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Usuario(Integer id, String nombre, String apellidoPaterno,
                   String apellidoMaterno, String email, String password,
                   String telefono, Rol rol, LocalDateTime creadoEn,
                   LocalDateTime actualizadoEn){
        this.id=id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.rol = rol;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public Integer getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApellidoPaterno(){
        return this.apellidoPaterno;
    }

    public String getApellidoMaterno(){
        return this.apellidoMaterno;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public Rol getRol(){
        return this.rol;
    }

    public LocalDateTime getCreadoEn(){
        return this.creadoEn;
    }

    public LocalDateTime getActualizadoEn(){
        return this.actualizadoEn;
    }

}
