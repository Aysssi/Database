package com.example.gamestore.domain.dtos;

import com.example.gamestore.domain.entities.Role;

public class UserDto {

    private String fullName;
    private String email;
    private Role role;

    public UserDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
}

