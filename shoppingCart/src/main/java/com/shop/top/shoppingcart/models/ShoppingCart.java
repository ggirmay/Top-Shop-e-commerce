package com.shop.top.shoppingcart.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Setter
@Getter
public class ShoppingCart {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long Id;

    private Long userId;

    private int quantity;

    private float totalPrice;

    @OneToMany(mappedBy = "shoppingCart")
    private List<ItemDetail> itemDetails;
}
