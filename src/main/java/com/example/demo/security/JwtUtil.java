package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public boolean validateToken(String token) {
        return true;
    }

    public String extractUsername(String token) {
        return "test@test.com";
    }
}
