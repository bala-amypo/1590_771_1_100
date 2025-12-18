// package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.model.User;
// import com.example.demo.service.UserService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;
// import io.swagger.v3.oas.annotations.tags.Tag;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication Controller")
// public class AuthController<LoginRequest> {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/register")
//     public ResponseEntity<User> register(@RequestBody User user) {
//         return ResponseEntity.ok(userService.register(user));
//     }

//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//         return ResponseEntity.ok("JWT token placeholder");
//     }
// }
