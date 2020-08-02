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

    @Query(value = "select order_id, amount, created_date, status, user_id, user_name " +
            "from orders where user_id = :userid", nativeQuery = true)
    List<Orders> selectAllOrdersOfSpecificUser(@Param("userid") Long userId);
}
