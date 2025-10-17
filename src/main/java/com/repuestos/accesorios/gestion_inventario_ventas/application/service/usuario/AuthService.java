package com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.login.LoginRequest;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.login.LoginResponse;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.RegistroUsuarioDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.usuario.UsuarioMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.*;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.persona.Persona;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.rol.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.persona.PersonaWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.rol.RolRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioWriteRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UsuarioWriteRepository usuarioWriteRepository;
    private final PersonaWriteRepository personaWriteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RolRepository rolRepository;

    public AuthService(UsuarioWriteRepository usuarioWriteRepository,
                       PersonaWriteRepository personaWriteRepository, PasswordEncoder passwordEncoder,
                       JwtProvider jwtProvider,
                       RolRepository rolRepository){
        this.usuarioWriteRepository = usuarioWriteRepository;
        this.personaWriteRepository = personaWriteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.rolRepository = rolRepository;
    }

    public void register(RegistroUsuarioDto registerRequest){
        personaWriteRepository.findByCorreo(registerRequest.getPersona().getCorreo())
                .ifPresent(u ->{ throw new EmailYaRegistradoException("El email ya está registrado");});
        Rol rol = rolRepository.findById(registerRequest.getRolId())
                .orElseThrow(() -> new BusinessException("Rol no encontrado"));
        String hash = passwordEncoder.encode(registerRequest.getContrasenia());
        Usuario nuevo = UsuarioMapper.from(registerRequest, hash, rol);
        usuarioWriteRepository.save(nuevo);
    }
    public LoginResponse login(LoginRequest request){
        Persona persona = personaWriteRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        Usuario usuario = usuarioWriteRepository.findByPersonaId(persona.getId())
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

        List<String> roles = claims.get("rol", List.class);
        String newAccess = jwtProvider.generateAccessToken(userId,  roles);
        String newRefresh = jwtProvider.generateRefreshToken(userId, roles);
        return new LoginResponse(newAccess, newRefresh); }
}
