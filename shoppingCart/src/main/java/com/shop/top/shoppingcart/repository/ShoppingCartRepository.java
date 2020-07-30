package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
