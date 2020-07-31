package com.top.shop.user.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Address address;
    private String moto;
    private String imageLogoName;
    @OneToMany
    private List<PaymentInformation> paymentInformation;

}
