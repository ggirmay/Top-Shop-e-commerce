package com.top.shop.user.domain;

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

    public UserAccount(Long id, @Email String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.enabled = false;
    }

    public UserAccount() {

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
