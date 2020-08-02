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
    Long orderId;

    @Column("product_id")
    Long productId;

    @Column("product_name")
    String productName;

    int quantity;

    @Column("unit_price")
    float unitPrice;

    @Column("subtotal_price")
    float subtotalPrice;
}
