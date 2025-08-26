package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DomainUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        String roleName = usuario.getRol() != null && usuario.getRol().getNombre() != null
                ? usuario.getRol().getNombre().toUpperCase() : "USER";

        return User.withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities("ROLE_" + roleName)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
