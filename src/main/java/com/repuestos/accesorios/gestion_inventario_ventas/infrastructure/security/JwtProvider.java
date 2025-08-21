package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey key;
    private final long accessTtlSeconds;
    private final long refreshTtlSeconds;
    private final String issuer;

    public JwtProvider(@Value("${security.jwt.secret}") String secret,
                       @Value("${security.jwt.issuer:repuestos-ms}") String issuer,
                       @Value("${security.jwt.access-ttl-seconds:900}") long accessTtlSeconds,        // 15 min
                       @Value("${security.jwt.refresh-ttl-seconds:1209600}") long refreshTtlSeconds  // 14 días
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.issuer = issuer;
        this.accessTtlSeconds = accessTtlSeconds;
        this.refreshTtlSeconds = refreshTtlSeconds;
    }

    public String generateAccessToken(Integer usuarioId, String email, String rol){
        return generateToken(usuarioId, email, rol, accessTtlSeconds, false);
    }

    // Sobrecarga simple para refresh token
    public String generateRefreshToken(Integer usuarioId, String email, String rol) {
        return generateToken(usuarioId, email, rol, refreshTtlSeconds, true);
    }

    private String generateToken(Integer usuarioId, String email, String rol, long ttl, boolean refresh){
        Instant now = Instant.now();
        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttl)))
                .claim("uid", usuarioId)
                .claim("email", email)
                .claim("rol", rol)
                .claim("typ", refresh ? "refresh" : "access")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateAndGetClaims(String token, boolean expectRefresh) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String typ = claims.get("typ", String.class);
        if (expectRefresh && !"refresh".equals(typ)) {
            throw new JwtException("Se esperaba un token de refresh");
        }
        if (!expectRefresh && !"access".equals(typ)) {
            throw new JwtException("Se esperaba un token de acceso");
        }
        return claims;
    }
}

