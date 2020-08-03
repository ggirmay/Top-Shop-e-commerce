package com.shop.top.shoppingcart.repository;

import com.shop.top.shoppingcart.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
//    @Query(value = "select * from orders o left join order_detail od " +
//            "on o.order_id = od.order_id where o.user_id = :userid", nativeQuery = true)
//    List<Object> selectOrderWithAllOrderDetail(@Param("userid")Long userId);

    List<Orders> findAllByUserId(Long userId);

    @Query(value = "select orders.order_id, orders.amount, orders.created_date, orders.status, orders.user_id, " +
            "orders.user_name from orders where orders.user_id = :userid", nativeQuery = true)
    List<Orders> selectAllOrdersOfSpecificUser(@Param("userid") Long userId);
}
