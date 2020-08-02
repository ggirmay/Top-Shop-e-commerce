package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository <OrderDetail, Long> {
}
