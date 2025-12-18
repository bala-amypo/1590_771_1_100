// package com.example.demo.model;

// import java.time.LocalDateTime;

// import javax.management.relation.Role;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long id;
//     private String fullName;
//     private String email;
//     private String password;
//     private Role role;
//     private LocalDateTime createdAt;

//     public User(){}

//     public User(String fullName, String email, String password, Role role, LocalDateTime createdAt) {
//         this.fullName = fullName;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//         this.createdAt = createdAt;
//     }

//     public long getId() {
//         return id;
//     }

//     public void setId(long id) {
//         this.id = id;
//     }

//     public String getFullName() {
//         return fullName;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public Role getRole() {
//         return role;
//     }

//     public void setRole(Role role) {
//         this.role = role;
//     }

//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }


    
// }


   
    
