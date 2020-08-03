package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class RegisteredUser  extends User{

    @OneToOne
    private UserAccount userAccount;
    private Role user_role;

        public RegisteredUser() {
            super();
            this.user_role = Role.REGISTERED_USER;
        }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Role getRole() {
        return user_role;
    }
}
