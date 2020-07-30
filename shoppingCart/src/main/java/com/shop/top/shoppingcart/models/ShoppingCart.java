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
    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    private Long userId;

    @OneToMany(mappedBy = "ShoppingCart")
    private List<ItemDetail> itemDetails;
}
