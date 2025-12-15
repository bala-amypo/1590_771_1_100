package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.context.support.BeanDefinitionDsl.Role;

public class User {
    private long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;

    public class User(){}

    

    
    
}
