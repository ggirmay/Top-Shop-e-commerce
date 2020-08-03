package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

import javax.persistence.Entity;

@Entity
public class GuestUser extends User{
    private Role role;
    public GuestUser() {
        super();
        this.role=Role.GUEST_USER;
    }
}
