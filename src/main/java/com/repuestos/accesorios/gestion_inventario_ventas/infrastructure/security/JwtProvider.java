package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    private final SecretKey key;
    private final Duration accessTtl;
    private final Duration  refreshTtl;
    private final String issuer;

    public JwtProvider(@Value("${security.jwt.secret}") String secret,
                       @Value("${security.jwt.issuer:repuestos-ms}") String issuer,
                       @Value("${security.jwt.access-ttl}") Duration  accessTtl,
                       @Value("${security.jwt.refresh-ttl}") Duration  refreshTtl
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.issuer = issuer;
        this.accessTtl = accessTtl;
        this.refreshTtl = refreshTtl;
    }

    public String generateAccessToken(Integer usuarioId,  List<String> roles){
        return generateToken(usuarioId, roles, accessTtl, false);
    }

    public String generateRefreshToken(Integer usuarioId, List<String> roles) {
        return generateToken(usuarioId, roles, refreshTtl, true);
    }

    private String generateToken(Integer usuarioId, List<String> roles, Duration ttl, boolean refresh){
        Instant now = Instant.now();
        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(ttl)))
                .claim("uid", usuarioId)
                .claim("rol", roles)
                .claim("typ", refresh ? "refresh" : "access")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateAndGetClaims(String token, boolean expectRefresh) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .setAllowedClockSkewSeconds(60)
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
        } catch (io.jsonwebtoken.ExpiredJwtException e){
            throw new JwtException("El token ha expirado");
        } catch (JwtException e) {
            throw new JwtException("Token inválido: " + e.getMessage());
        }
    }
}

