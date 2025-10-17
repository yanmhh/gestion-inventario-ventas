package com.repuestos.accesorios.gestion_inventario_ventas.application.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    private String contrasenia;

    public String getCorreo(){
        return this.correo;
    }

    public void setCorreo(String email){
        this.correo = email;
    }

    public String getContrasenia(){
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
}
