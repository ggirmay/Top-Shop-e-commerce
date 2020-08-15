package com.shop.top.shoppingcart.controllers;

import com.shop.top.shoppingcart.dto.OrderDTO;
import com.shop.top.shoppingcart.dto.PaymentInformation;
import com.shop.top.shoppingcart.dto.PlaceOrderDTO;
import com.shop.top.shoppingcart.models.OrderDetail;
import com.shop.top.shoppingcart.models.Orders;
import com.shop.top.shoppingcart.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/placeorder" )
    public Orders placeOrder ( @RequestBody PlaceOrderDTO data ) throws NumberFormatException, ParseException{
    	    	
    	OrderDTO orderDTO = data.getOrder();
    	PaymentInformation paymentInfo = data.getCard();
    	
    	Orders order = new Orders();
    	order.setAmount(orderDTO.getAmount());
    	order.setCreatedDate(orderDTO.getCreatedDate());
    	order.setOrderDetails(orderDTO.getOrderDetails());
    	order.setStatus(orderDTO.getStatus());
    	
    	long id = orderDTO.getUserId().startsWith("G") ? 0 : Long.valueOf(orderDTO.getUserId().split("-")[1]);
    	order.setUserId(id);
    	order.setUserName(orderDTO.getUserName());
    	
        System.out.println("this is order controller");
        try {
            return orderService.placeOrder(order , paymentInfo);
        }catch (Exception e){
        	e.printStackTrace();
        	System.err.println(e.getMessage());
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
        return orderService.selectOrdersWithAllOrderDetailForSpecificUser(userId);
    }

    @GetMapping("/allorders")
    public List<Orders> allOrders(){
        return orderService.selectAllOrders();
    }
}