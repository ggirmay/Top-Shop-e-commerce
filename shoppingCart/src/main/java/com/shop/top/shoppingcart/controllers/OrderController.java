package com.shop.top.shoppingcart.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @PostMapping(value = "/placeorder", consumes = "application/json", produces = "application/json")
    public Orders placeOrder(@RequestBody Orders order){
        try {
            return orderService.placeOrder(order);
        }catch (Exception e){
            System.out.println("this is in order" + e.getMessage());
            order.setError(e.getMessage());
            return order;
        }
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
