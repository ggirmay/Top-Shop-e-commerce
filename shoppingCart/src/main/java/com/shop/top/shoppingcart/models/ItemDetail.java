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
    @Column(name = "item_id")
    Long ItemId;

    @NotNull
    @Column(name = "shopping_cart_id")
    Long shoppingCartId;

    @NotNull
    @Column(name = "product_id")
    Long productId;

    @Column(name = "product_name")
    String productName;

    @Column(name = "unit_price")
    float unitPrice;

    @Column(name = "sub_total")
    float subTotal;

    int quantity;
    // status has three values "Active = A", "Payed = P" and "Deleted = D"
    char status;

    @ManyToOne()
    @JoinColumn(name = "shopping_cart_id")
    ShoppingCart shoppingCart;
}
