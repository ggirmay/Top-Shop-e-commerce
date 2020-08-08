package com.top.shop.user.domain;

import com.top.shop.user.util.Role;
import lombok.Data;

import javax.persistence.*;
/**
 * @author Yome Mengistu
 */
@Entity
@Data
public class Admin{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private UserAccount account;
    public UserAccount getAccount() {
        return account;
    }
    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.account = userAccount;
        userAccount.setRole("ADMIN");
    }
}
