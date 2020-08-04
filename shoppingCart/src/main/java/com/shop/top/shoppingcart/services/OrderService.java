package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.models.Orders;
import com.shop.top.shoppingcart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Orders placeOrder(Orders order){
        return orderRepository.save(order);
    }

    public List<Orders> selectAllOrders(){
        return orderRepository.findAll();
    }

    public List<Orders> selectAllOrdersForSpecificUser(Long userId){
        return orderRepository.selectAllOrdersOfSpecificUser(userId);
    }

    public List<Orders> selectOrderWithAllOrderDetail(Long userId){
        return orderRepository.findAllByUserId(userId);
    }
}
