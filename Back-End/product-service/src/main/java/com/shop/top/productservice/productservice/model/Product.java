package com.shop.top.productservice.productservice.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
   // @ManyToOne
    private String category;

    @OneToOne
    private ProductDetail productDetail;

    private double salesPrice;

    private double discount;
    private String shortDetails;
    private String description;
    private boolean newPro;
    private boolean sale;
    private String brand;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Picture> pictures;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tag",
            joinColumns = @JoinColumn(name="product_id"))
    private Set<String> tags = new HashSet<String>();

    public void addpicture(String imageNmae){

    }
}
