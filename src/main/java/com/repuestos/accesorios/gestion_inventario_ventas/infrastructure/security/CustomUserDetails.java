package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.EstadoUsuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final Usuario usuario;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (usuario.getRol() != null && usuario.getRol().getNombre() != null) {
            return Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre().toUpperCase())
            );
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usuario.getContrasenia();
    }

    @Override
    public String getUsername() {
        if (usuario.getPersona() == null || usuario.getPersona().getCorreo() == null) {
            return "desconocido";
        }
        return usuario.getPersona().getCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuario.getEstado() == EstadoUsuario.ACTIVO;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
