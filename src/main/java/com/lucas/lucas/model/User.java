package com.lucas.lucas.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String last_name;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String tel;
}
