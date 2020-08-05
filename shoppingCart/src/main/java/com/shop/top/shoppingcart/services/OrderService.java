package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.models.Orders;
import com.shop.top.shoppingcart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;


@Service
public class OrderService {

    OrderRepository orderRepository;

    RestTemplate restTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate){
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public Orders placeOrder(Orders order){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String , String> map = new HashMap<>();

        map.put("cardNumber" , "4351930605662860");
        map.put("nameOnCard" , "ali ansari");
        map.put("securityDigit" , "123");
        map.put("expirationDate" , "2020-08-11"); // Localdate -> String
        map.put("amount" , "" + order.getAmount());

        HttpEntity<HashMap<String , String >> request = new HttpEntity<>(map, httpHeaders);

        HashMap result = restTemplate.postForObject("http://localhost:8088/payment/checkout",request, HashMap.class);

        System.out.println("This is order service");
        System.out.println(result.get("amount"));
        System.out.println(result.get("valid"));

//        if (request.equals(true)){
//            return orderRepository.save(order);
//        }else {
//            throw new Exception("You card verification ")
//        }
        return null;
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
