package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Vendor{
    @Id
    private Long id;
    @OneToOne
   private UserAccount userAccount;
   private Role role;

    public Vendor(){
       this.role=Role.VENDOR;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Role getRole() {
        return role;
    }
}
