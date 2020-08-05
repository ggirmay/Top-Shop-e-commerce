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

        HttpEntity<HashMap<String , String >> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<Boolean> result = restTemplate.postForEntity("http://payment-service/payment/checkout",request, Boolean.class);


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
