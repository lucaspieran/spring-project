package com.lucas.lucas.repository;

import com.lucas.lucas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> getByEmail(String email);
}
