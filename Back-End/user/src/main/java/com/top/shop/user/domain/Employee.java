package com.top.shop.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.top.shop.user.util.Role;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Requirement Giver and Requirement receiver
 * @author Yome Mengistu
 */
@Entity
@EqualsAndHashCode
public class Employee{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long Id;
    @OneToOne(cascade = CascadeType.ALL)
    private UserAccount userAccount;
    private String role;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor;

    public Employee(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}