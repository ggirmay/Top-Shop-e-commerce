package com.shop.top.productservice.productservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private String type;
    @ManyToOne
    private Category category;
    @OneToMany
    private List<ProductDetail> productDetailList;
}
