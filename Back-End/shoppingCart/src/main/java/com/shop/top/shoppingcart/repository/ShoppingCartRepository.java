package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.ItemDetail;
import com.shop.top.shoppingcart.models.ShoppingCart;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findAllByItemDetailsEmpty();

}