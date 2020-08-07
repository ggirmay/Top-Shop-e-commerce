package com.top.shop.user.domain;

import com.top.shop.user.util.Role;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author Yome Mengistu
 */
@Entity
@ToString
public class RegisteredUser  extends User{

    @OneToOne(cascade = CascadeType.ALL)
    private UserAccount userAccount;


    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        userAccount.setRole("REGISTERED_USER");
    }

}
