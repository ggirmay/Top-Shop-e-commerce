package com.top.shop.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
public class UserAccount {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Email
    @NotNull
    private String email;
    private String password;
    private boolean enabled;
    private boolean rejected = false;
    private String username;
    private String Role;
    private String firstName;
    private String lastName;
    @Transient
    private Long user_id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", cascade = { CascadeType.ALL }, orphanRemoval = true)
    VerificationToken verificationToken;
    public void enable(){
        this.enabled = true;
    }

}
