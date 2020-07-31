package com.top.shop.user.domain;

import com.sun.istack.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Email;
/**
 * Base class for all accounts(Admin, vendor, Registered users...)
 * It sole purpose is authorization and authentication.
 * @author Yome Mengistu
 */

@Entity
public class UserAccount {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Email
    @NotNull
    private String email;
    private String password;

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


}
