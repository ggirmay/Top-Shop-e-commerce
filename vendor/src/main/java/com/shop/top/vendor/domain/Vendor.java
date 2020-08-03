package com.shop.top.vendor.domain;

import com.shop.top.vendor.util.Role;
import lombok.Data;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import sun.jvm.hotspot.debugger.cdbg.EnumType;

import javax.persistence.*;

@Data
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

//    @Enumerated (value = EnumType.STRING)
//    @Column(name = "role")
    private Role role;



}