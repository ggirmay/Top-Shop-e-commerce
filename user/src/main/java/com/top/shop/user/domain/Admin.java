package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

import javax.persistence.*;
/**
 * @author Yome Mengistu
 */
@Entity
public class Admin{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    @OneToOne
    private UserAccount account;
    private Role role;
    public Admin(){
        this.role=Role.ADMINISTRATOR;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }
}
