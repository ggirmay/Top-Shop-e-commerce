package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
