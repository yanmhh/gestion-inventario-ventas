package com.repuestos.accesorios.gestion_inventario_ventas.application.service;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.LoginRequest;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.LoginResponse;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.RegisterUserCommand;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.UsuarioMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.BusinessException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNotFoundException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.Rol;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.RolRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.UsuarioRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RolRepository rolRepository;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtProvider jwtProvider,
                       RolRepository rolRepository){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.rolRepository = rolRepository;
    }

    public void register(RegisterUserCommand registerRequest){
        usuarioRepository.findByEmail(registerRequest.getEmail())
                .ifPresent(u ->{ throw new BusinessException("El email ya está registrado");});

        Rol rol = rolRepository.findById(registerRequest.getRolId()).orElseThrow(() -> new BusinessException("Rol no encontrado"));

        String hash = passwordEncoder.encode(registerRequest.getPassword());

        Usuario nuevo = UsuarioMapper.from(registerRequest, rol, hash);

        usuarioRepository.save(nuevo);
    }

    public LoginResponse login(LoginRequest request){
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        if(!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new BusinessException("Credenciales invalidas");
        }

        String access = jwtProvider.generateAccessToken(usuario.getId(), usuario.getEmail(), usuario.getRol().getNombre());

        String refresh = jwtProvider.generateRefreshToken(usuario.getId(), usuario.getEmail(), usuario.getRol().getNombre());
        return new LoginResponse(access,refresh);
    }

    public LoginResponse refresh(String refreshToken){
        var claims = jwtProvider.validateAndGetClaims(refreshToken, true);
        Integer userId = claims.get("uid",Integer.class);
        String email = claims.get("email",String.class);
        String rol = claims.get("rol", String.class);

        String newAccess = jwtProvider.generateAccessToken(userId, email, rol);
        String newRefresh = jwtProvider.generateRefreshToken(userId, email, rol);

        return new LoginResponse(newAccess, newRefresh);

    }
}
