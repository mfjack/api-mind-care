package com.mindcare.api_mind_care.dto;

import com.mindcare.api_mind_care.domain.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateRequestDTO {
    @NotBlank
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password should be at least 6 characters long")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password;
    private Role role;

    public UserCreateRequestDTO() {
    }

    public UserCreateRequestDTO(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
