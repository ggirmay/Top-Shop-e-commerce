package com.shop.top.shoppingcart.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@EqualsAndHashCode
public class ItemDetail {

    public ItemDetail() {
    }

    public ItemDetail(Long productId, String productName, float unitPrice, int quantity, float subTotal, char status) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.status = status;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    Long ItemId;

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

//    @ManyToOne()
//    @JoinColumn(name = "shopping_cart_id", nullable = false)
//    ShoppingCart shoppingCart;
}
