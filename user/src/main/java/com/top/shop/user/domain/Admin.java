package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Admin{
    @Id
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
