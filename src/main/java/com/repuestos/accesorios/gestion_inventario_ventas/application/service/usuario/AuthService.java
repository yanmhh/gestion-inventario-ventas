package com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.login.LoginRequest;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.login.LoginResponse;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.RegistroUsuarioDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.usuario.UsuarioMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.*;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.rol.RolRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RolRepository rolRepository;

    public AuthService(UsuarioRepository usuarioRepository,
                       PersonaRepository personaRepository, PasswordEncoder passwordEncoder,
                       JwtProvider jwtProvider,
                       RolRepository rolRepository){
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.rolRepository = rolRepository;
    }

    public void register(RegistroUsuarioDto registerRequest){
        personaRepository.findByCorreo(registerRequest.getPersona().getCorreo())
                .ifPresent(u ->{ throw new EmailYaRegistradoException("El email ya está registrado");});
        Rol rol = rolRepository.findById(registerRequest.getRolId())
                .orElseThrow(() -> new BusinessException("Rol no encontrado"));
        String hash = passwordEncoder.encode(registerRequest.getContrasenia());
        Usuario nuevo = UsuarioMapper.from(registerRequest, hash, rol);
        usuarioRepository.save(nuevo);
    }
    public LoginResponse login(LoginRequest request){
        Persona persona = personaRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        Usuario usuario = usuarioRepository.findByPersonaId(persona.getId())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.asegurarActivo();

        if(!passwordEncoder.matches(request.getContrasenia(),
                usuario.getContrasenia())) { throw new BusinessException("Credenciales invalidas");
        }
        List<String> roles = List.of(usuario.getRol().getNombre());

        String access = jwtProvider.generateAccessToken(usuario.getId(),
                 roles);
        String refresh = jwtProvider.generateRefreshToken(usuario.getId(),
                 roles);

        return new LoginResponse(access,refresh);
    }
    public LoginResponse refresh(String refreshToken){
        var claims = jwtProvider.validateAndGetClaims(refreshToken, true);
        Integer userId = claims.get("uid",Integer.class);

        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) claims.get("rol");
        String newAccess = jwtProvider.generateAccessToken(userId,  roles);
        String newRefresh = jwtProvider.generateRefreshToken(userId, roles);
        return new LoginResponse(newAccess, newRefresh); }
}
