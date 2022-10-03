package com.admiralxy.restful.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.security.jwt.token.secret}")
    private String secret;

    @Value("${spring.security.jwt.token.expired}")
    private long validityInMilliseconds;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String create(Long id, String login, String role) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("id", id);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String resolve(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public JwtUser validate(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            if (!claims.getExpiration().before(new Date())) {
                String login = claims.getSubject();
                Long id = claims.get("id", Long.class);
                String role = claims.get("role", String.class);
                return new JwtUser(id, login, role);
            }
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
        return null;
    }

    public long getExpirationSeconds() {
        return validityInMilliseconds / 1000;
    }
}
