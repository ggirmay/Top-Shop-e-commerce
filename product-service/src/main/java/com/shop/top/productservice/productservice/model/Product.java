package com.shop.top.productservice.productservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ProductStatus status=ProductStatus.pending;
    private String name;
    private String code;
    private String type;
    private Double price;
    private String pictureUrl;
    @ManyToOne
    private Category category;
    @OneToMany
    private List<ProductDetail> productDetailList;

}
