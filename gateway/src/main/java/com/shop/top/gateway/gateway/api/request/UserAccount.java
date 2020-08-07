package com.shop.top.gateway.gateway.api.request;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAccount {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String username;
    private boolean enabled;

    public UserAccount() {
    }

    public UserAccount(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }



    public UserAccount(Long id, String email, String password, String role, String username, boolean enabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.username = username;
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userName='" + username + '\'' +
                ", enabled=" + enabled +
                '}';
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
