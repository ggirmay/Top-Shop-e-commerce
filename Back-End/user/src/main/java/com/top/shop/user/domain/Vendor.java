package com.top.shop.user.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Vendor {
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
    @JoinColumn(name = "p_info")
    private List<PaymentInformation> paymentInformation;
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private UserAccount userAccount;
    @OneToMany(
            mappedBy = "vendor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Employee> employees;


    public void addEmployee(Employee employee){
        employee.setVendor(this);
        this.employees.add(employee);
    }
    public void removeEmployee(Employee employee){
        this.employees.remove(employee);
    }

}
