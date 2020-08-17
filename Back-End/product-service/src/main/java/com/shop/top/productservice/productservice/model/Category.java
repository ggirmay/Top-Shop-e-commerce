package com.shop.top.productservice.productservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
//    private Set<Product> products;
}
