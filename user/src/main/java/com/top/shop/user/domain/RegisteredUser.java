package com.top.shop.user.domain;

import com.top.shop.user.util.Role;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * @author Yome Mengistu
 */
@Entity
@ToString
public class RegisteredUser  extends User{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private UserAccount userAccount;
    private Role user_role;


    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Role getRole() {
        return user_role;
    }

    public void setUser_role(Role user_role) {
        this.user_role = Role.REGISTERED_USER;
    }
}
