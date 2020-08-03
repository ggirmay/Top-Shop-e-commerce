package com.shop.top.payment.payment.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false , unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {
    }

    public User(String lastName, String firstName, String username , String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        this.roles.add(new Role("USER"));
    }

    public User(String lastName, String firstName , String username, String password, Role role) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;

        this.roles = new ArrayList<>();
        this.roles.add(role);
    }

    public User(String lastName, String firstName, String username, String password, List<Role> roles) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
