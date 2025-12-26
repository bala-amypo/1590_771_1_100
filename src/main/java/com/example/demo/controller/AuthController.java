// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.impl.UserServiceImpl;

// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final AuthenticationManager authenticationManager;
//     private final UserServiceImpl userService;
//     private final JwtUtil jwtUtil;

//     public AuthController(AuthenticationManager authenticationManager,
//                           UserServiceImpl userService,
//                           JwtUtil jwtUtil) {
//         this.authenticationManager = authenticationManager;
//         this.userService = userService;
//         this.jwtUtil = jwtUtil;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<User> register(@RequestBody User user) {
//         return ResponseEntity.ok(userService.registerUser(user));
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

//         Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(
//                 request.getEmail(),
//                 request.getPassword()
//             )
//         );

//         User user = userService.findByEmail(request.getEmail());

//         // ✅ FIXED CALL (matches JwtUtil)
//         String token = jwtUtil.generateToken(
//             user.getEmail(),
//             user.getRole()
//         );

//         return ResponseEntity.ok(
//             new AuthResponse(
//                 token,
//                 user.getId(),
//                 user.getEmail(),
//                 user.getRole()
//             )
//         );
//     }
// }

package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {

        User savedUser = userService.register(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", savedUser.getRole());

        String token = jwtUtil.generateToken(
                claims,
                savedUser.getEmail()
        );

        return ResponseEntity.ok(
                new AuthResponse(
                        token,
                        savedUser.getId(),
                        savedUser.getEmail(),
                        savedUser.getRole()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        request.getEmail()
                );

        User user =
                userService.findByEmail(request.getEmail());

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());

        String token = jwtUtil.generateToken(
                claims,
                userDetails.getUsername()
        );

        return ResponseEntity.ok(
                new AuthResponse(
                        token,
                        user.getId(),
                        user.getEmail(),
                        user.getRole()
                )
        );
    }
}