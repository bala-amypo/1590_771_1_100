// package com.example.demo.service.impl;

// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserService {

//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public User register(User user) {
//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new IllegalArgumentException("Email already registered");
//         }
//         return userRepository.save(user);
//     }

//     @Override
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
//     }

//     @Override
//     public User getUser(Long id) {
//         return userRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
//     }
// }
