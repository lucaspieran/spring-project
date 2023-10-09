package com.lucas.lucas.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "El campo 'name' es obligatorio")
    private String name;

    @NotBlank(message = "El campo 'last_name' es obligatorio")
    private String lastName;

    @Email(message = "El campo 'email' debe ser una dirección de correo electrónico válida")
    @NotBlank(message = "El campo 'email' es obligatorio")
    private String email;
}
