package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
