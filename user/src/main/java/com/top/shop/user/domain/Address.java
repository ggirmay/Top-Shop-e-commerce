package com.top.shop.user.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;

import javax.persistence.*;
/**
 * @author Yome Mengistu
 */
@Entity
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String addressLineOne;
    @NotNull
    private String addressLineTwo;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }
}
