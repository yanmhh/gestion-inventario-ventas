package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioDetallesUsuario implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public ServicioDetallesUsuario(
            UsuarioRepository usuarioRepository,
            PersonaRepository personaRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Correo no registrado"));

        Usuario usuario = usuarioRepository.findById(persona.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getEstado() != com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.EstadoUsuario.ACTIVO) {
            throw new UsernameNotFoundException("Usuario inactivo");
        }

        return new CustomUserDetails(usuario);
    }


    public UserDetails loadUserById(Integer usuarioId) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getEstado() != com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.EstadoUsuario.ACTIVO) {
            throw new UsernameNotFoundException("Usuario inactivo");
        }

        return new CustomUserDetails(usuario);
    }
}
