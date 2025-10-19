package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtProvider jwtProvider;
    private final ServicioDetallesUsuario servicioDetallesUsuario;

    public JwtAuthFilter(JwtProvider jwtProvider, ServicioDetallesUsuario servicioDetallesUsuario) {
        this.jwtProvider = jwtProvider;
        this.servicioDetallesUsuario = servicioDetallesUsuario;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = jwtProvider.validateAndGetClaims(token, false);
                Integer userId = claims.get("uid", Integer.class);

                // Cargar UserDetails completo con tu servicio
                UserDetails userDetails = servicioDetallesUsuario.loadUserById(userId);

                // Construir autenticación con UserDetails y sus roles
                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception ex) {
                org.springframework.security.core.context.SecurityContextHolder.clearContext();

                logger.error("Error al validar el token JWT: {}", ex.getMessage(), ex);

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Token inválido o expirado\"}");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
