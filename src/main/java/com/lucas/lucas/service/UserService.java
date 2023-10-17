package com.lucas.lucas.service;

import com.lucas.lucas.model.User;
import com.lucas.lucas.model.UserRole;
import com.lucas.lucas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll () {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public User createUser(User user) {
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }
        return null;
    }
        public Optional<User> getUserByEmail(String email) {
            return userRepository.getByEmail(email);
        }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
