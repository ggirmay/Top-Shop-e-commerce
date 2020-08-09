package com.shop.top.productservice.productservice.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Product implements Serializable {


//    public Product( byte[] picByte) {
//        this.picByte = picByte;
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    ProductStatus status=ProductStatus.pending;

    private String code;
    private String type;
    private Double price;
    private  int quantity;

    private String picture_url;
    @ManyToOne
    private Category category;

@OneToOne
    private ProductDetail productDetail;

}
