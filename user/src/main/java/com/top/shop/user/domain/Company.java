package com.top.shop.user.domain;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    private String name;
    private String moto;
    private String imageLogoName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PaymentInformation> paymentInformation;

    public Company(Long id,String name, Address address, String moto, String imageLogoName, List<PaymentInformation> paymentInformation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.moto = moto;
        this.imageLogoName = imageLogoName;
        this.paymentInformation = paymentInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMoto() {
        return moto;
    }

    public void setMoto(String moto) {
        this.moto = moto;
    }

    public String getImageLogoName() {
        return imageLogoName;
    }

    public void setImageLogoName(String imageLogoName) {
        this.imageLogoName = imageLogoName;
    }

    public List<PaymentInformation> getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(List<PaymentInformation> paymentInformation) {
        this.paymentInformation = paymentInformation;
    }
}
