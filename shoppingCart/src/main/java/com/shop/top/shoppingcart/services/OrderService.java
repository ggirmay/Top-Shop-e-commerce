package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.models.Order;
import com.shop.top.shoppingcart.models.OrderDetail;
import com.shop.top.shoppingcart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

//    public Order placeOrder(Long userId, String userName, String status, float amount, List<OrderDetail>){
//
//    }
}
