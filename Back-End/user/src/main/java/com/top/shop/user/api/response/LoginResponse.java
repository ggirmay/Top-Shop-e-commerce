package com.top.shop.user.api.response;

public class LoginResponse {
    Long id;
    String email;
    String Role;
    String name;

    public LoginResponse(Long id, String email, String role, String name) {
        this.id = id;
        this.email = email;
        Role = role;
        this.name = name;
    }

    public LoginResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
