package com.shop.top.shoppingcart.controllers;

import com.shop.top.shoppingcart.models.OrderDetail;
import com.shop.top.shoppingcart.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {

    OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/specificorder/{orderid}")
    public List<OrderDetail> selectOrderDetailOfSpecificOrder(@PathVariable("orderid") Long orderId){
        return orderDetailService.selectOrderDetailOfSpecificOrder(orderId);
    }
}
