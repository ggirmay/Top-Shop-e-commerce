package com.top.shop.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
/**
 * Base class for all accounts(Admin, vendor, Registered users...)
 * It sole purpose is authorization and authentication.
 * @author Yome Mengistu
 */

@Entity
@ToString
public class UserAccount {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Email
    @NotNull
    private String email;
    private String password;
    private boolean enabled;
    private String username;
    private String Role;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    VerificationToken verificationToken;

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public UserAccount(Long id, @Email String email, String password, boolean enabled, String username, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.username = username;
        Role = role;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public UserAccount() {

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void enable(){
        this.enabled = true;
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

    public void register(){
//        sendEmail();

    }
}
