package com.top.shop.user.domain;

import com.sun.istack.Nullable;
import com.top.shop.user.util.Role;

import javax.persistence.*;
/**
 * @author Yome Mengistu
 */
@Entity
public class Vendor{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    @OneToOne
    private Company company;
    @OneToOne
    private UserAccount userAccount;
    private Role role;
    @OneToOne
    private Address address;

    public Vendor(){

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
