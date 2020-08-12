package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.models.OrderDetail;
import com.shop.top.shoppingcart.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    OrderDetailRepository orderDetailRepository;

    //Janvier
    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> selectOrderDetailOfSpecificOrder(Long orderId){
        return orderDetailRepository.findAllOrderDetailByOrderID(orderId);
    }

}
