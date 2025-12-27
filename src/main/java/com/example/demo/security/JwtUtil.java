package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct; // Changed from javax to jakarta

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Must match the key structure required by the technology stack [cite: 7, 345]
    private final String secret = "ThisIsAHighlySecureSecretKeyThatIsAtLeastThirtyTwoCharsLong!";
    private final long validityInMs = 3600000; // 1 hour
    private Key signingKey;

    @PostConstruct // Standard initialization after dependency injection [cite: 346]
    public void init() {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Generates a real JWT token with userId and role claims [cite: 348-349]
     */
    public String generateToken(Authentication authentication, Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}