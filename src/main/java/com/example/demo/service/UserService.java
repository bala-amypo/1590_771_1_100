package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User registerUser(User user); [cite: 238]
    User findByEmail(String email); [cite: 239]
    User getById(Long id); [cite: 240]
}