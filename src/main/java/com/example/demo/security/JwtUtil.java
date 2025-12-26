// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import org.springframework.stereotype.Component;

// import java.util.Date;

// @Component
// public class JwtUtil {

//     // ✅ DEFINE CONFIG HERE (NO SPRING INJECTION)
//     private static final String SECRET =
//             "my-super-secret-key-12345678901234567890";
//     private static final long EXPIRATION_MS =
//             60 * 60 * 1000; // 1 hour

//     public String generateToken(String email, String role) {

//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("role", role)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
//                 .signWith(SignatureAlgorithm.HS256, SECRET)
//                 .compact();
//     }

//     public String getUsernameFromToken(String token) {
//         return getClaims(token).getSubject();
//     }

//     public String getRoleFromToken(String token) {
//         return (String) getClaims(token).get("role");
//     }

//     public boolean validateToken(String token) {
//         try {
//             getClaims(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException ex) {
//             return false;
//         }
//     }

//     private Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(SECRET)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "my-super-secret-key-12345678901234567890"; // Must be >= 32 chars
    private static final long EXPIRATION_MS = 60 * 60 * 1000;

    // 1. Helper to generate a secure key
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)                 // Modern replacement for setSubject
                .claim("role", role)
                .issuedAt(new Date())           // Modern replacement for setIssuedAt
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey())      // Must use a Key object
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())    // Modern replacement for setSigningKey
                .build()
                .parseSignedClaims(token)       // Replacement for parseClaimsJws
                .getPayload();                 // Replacement for getBody
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
