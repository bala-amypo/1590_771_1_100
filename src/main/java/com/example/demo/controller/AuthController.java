package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // Constructor injection for all required components [cite: 9]
    public AuthController(UserService userService, 
                          AuthenticationManager authenticationManager, 
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // 1. Authenticate the user against the database 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        // 2. Fetch the user details to get the ID and Role [cite: 348]
        User user = userService.findByEmail(authRequest.getEmail());

        // 3. Generate a real JWT token using JwtUtil [cite: 348-349]
        String token = jwtUtil.generateToken(
                authentication, 
                user.getId(), 
                user.getEmail(), 
                user.getRole()
        );

        // 4. Return the real token in the response [cite: 179-180]
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
    }
}