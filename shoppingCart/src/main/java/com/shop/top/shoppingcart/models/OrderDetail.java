package com.shop.top.shoppingcart.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class OrderDetail {

    public OrderDetail() {
    }

    public OrderDetail(Long productId, String productName, int quantity, float unitPrice, float subtotalPrice){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotalPrice = subtotalPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_detail_id", nullable = false)
    Long orderDetailId;

    @Column(name = "product_id")
    Long productId;

    @Column(name = "product_name")
    String productName;

    int quantity;

    @Column(name = "unit_price")
    float unitPrice;

    @Column(name = "subtotal_price")
    float subtotalPrice;
}
