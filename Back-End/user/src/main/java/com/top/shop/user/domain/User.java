package com.top.shop.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.top.shop.user.util.Role;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
/**
 * Super class for registered and guest users
 * It sole purpose is hold common characterstics of both classes.
 * @author Yome Mengistu
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Role role;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Address> addressList;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<PaymentInformation> paymentInformation;

    public void setRole(Role role) {
        this.role = Role.USER;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<PaymentInformation> getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(List<PaymentInformation> paymentInformation) {
        this.paymentInformation = paymentInformation;
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
