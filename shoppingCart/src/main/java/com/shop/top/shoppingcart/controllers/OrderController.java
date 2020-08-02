package com.shop.top.shoppingcart.controllers;

import com.shop.top.shoppingcart.models.Orders;
import com.shop.top.shoppingcart.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeorder")
    public Orders placeOrder(@RequestBody Orders order){
        return orderService.placeOrder(order);
    }

    @GetMapping("/ordersofuser/{userid}")
    public List<Orders> selectAllOrdersOfUser(@PathVariable("userid") Long userId){
        return orderService.selectAllOrdersForSpecificUser(userId);
    }

    @GetMapping("/orderwithorderdetail/{userid}")
    public List<Orders> selectOrdersWithOrderDetail(@PathVariable("userid") Long userId){
        return orderService.selectOrderWithAllOrderDetail(userId);
    }
}
