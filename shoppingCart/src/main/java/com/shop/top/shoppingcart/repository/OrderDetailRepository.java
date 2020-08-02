package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository <OrderDetail, Long> {

    @Query(value = "select * from order_detail where order_id = :orderId", nativeQuery = true)
    List<OrderDetail> findAllOrderDetailByOrderID(@Param("orderId")Long orderId);
}
