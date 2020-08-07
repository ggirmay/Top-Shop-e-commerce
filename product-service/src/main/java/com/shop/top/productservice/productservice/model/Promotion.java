package com.shop.top.productservice.productservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
 private Date endDate;
 private double dicountAmount;
@OneToMany()
 private List<Product> product;
}
