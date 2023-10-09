package com.lucas.lucas.controller;

import com.lucas.lucas.model.User;
import com.lucas.lucas.model.UserRole;
import com.lucas.lucas.service.UserService;
import com.lucas.lucas.util.ErrorResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

       try {
           User user = new User();
           user.setName(userDTO.getName());
           user.setLast_name(userDTO.getLastName());
           user.setEmail(userDTO.getEmail());
           user.setRole(UserRole.USER);

           //Check if email exists
           Optional<User> email = userService.getUserByEmail(userDTO.getEmail());
            if (email.isPresent()) {
                return ErrorResponseUtil.handleErrorResponse("Ya existe un email registrado",HttpStatus.CONFLICT );
            }

           User createdUser = userService.createUser(user);

           Map<String, Object> response = new HashMap<>();
           response.put("message", "User created successfully");
           response.put("user", createdUser);

           return new ResponseEntity<>(response, HttpStatus.CREATED);
       } catch (Exception e) {
           return ErrorResponseUtil.handleErrorResponse("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
