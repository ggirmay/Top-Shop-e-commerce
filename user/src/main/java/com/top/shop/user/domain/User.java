package com.top.shop.user.domain;

import com.top.shop.user.util.Role;

import javax.persistence.*;
import java.util.List;
/**
 * Super class for registered and guest users
 * It sole purpose is hold common characterstics of both classes.
 * @author Yome Mengistu
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    private Long id;
    private Role role;
    @OneToMany
    private List<Address> addressList;
    @OneToMany
    private List<PaymentInformation> paymentInformation;
    public User() {
        this.role = Role.USER;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
