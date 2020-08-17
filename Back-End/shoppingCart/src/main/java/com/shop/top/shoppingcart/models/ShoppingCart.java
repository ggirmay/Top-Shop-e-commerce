package com.shop.top.shoppingcart.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class ShoppingCart {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    private Long userId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private List<ItemDetail> itemDetails = new ArrayList<>();
    
    public void addItemDetail(ItemDetail itemDetail) {
    	this.itemDetails.add(itemDetail);
    }
}
