package com.top.shop.user.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
/**
 * @author Yome Mengistu
 */
@Entity
@ToString
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
    private String addressLineTwo;
    @NotNull
    private String postcode;
    private Long  user_id;

    public Address() {
    }

    public Address(Long id, String city, String state, String addressLineOne, String addressLineTwo, Long user_id) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.user_id = user_id;
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
