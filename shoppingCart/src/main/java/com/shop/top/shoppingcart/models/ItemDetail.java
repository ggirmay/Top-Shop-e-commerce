package com.shop.top.shoppingcart.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
public class ItemDetail {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @NotNull
    @Column(name = "user_id")
    Long userId;

    @NotNull
    @Column(name = "product_id")
    Long productId;

    @Column(name = "product_name")
    String productName;

    int quantity;

    @Column(name = "unit_price")
    float unitPrice;

    @Column(name = "sub_total")
    float subTotal;

    @ManyToOne()
    @JoinColumn(name = "shopping_cart_id")
    ShoppingCart shoppingCart;
}
