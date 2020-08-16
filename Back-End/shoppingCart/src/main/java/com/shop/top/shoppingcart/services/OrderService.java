package com.shop.top.shoppingcart.services;

import com.shop.top.shoppingcart.dto.PaymentInformation;
import com.shop.top.shoppingcart.models.Orders;
import com.shop.top.shoppingcart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;


@Service
public class OrderService {

    OrderRepository orderRepository;
    ItemDetailService itemDetailService;
    RestTemplate restTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate
    , ItemDetailService itemDetailService){
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.itemDetailService = itemDetailService;
    }

    public Orders placeOrder(Orders order , PaymentInformation paymentInfo) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String , String> orderInfo = new HashMap<>();
        HashMap<Long, Integer> quantityInfo = new HashMap<>();

        orderInfo.put("cardNumber" , paymentInfo.getCardNumber());
        orderInfo.put("nameOnCard" , paymentInfo.getNameOnCard());
        orderInfo.put("securityDigit" , paymentInfo.getSecurityDigit());
        orderInfo.put("expirationDate" , paymentInfo.getExpirationDate()); // Localdate -> String
        orderInfo.put("amount" , "" + order.getAmount());
        HttpEntity<HashMap<String , String >> request = new HttpEntity<>(orderInfo, httpHeaders);

        int size = order.getOrderDetails().size();
        for (int i = 0; i < size; i++) {
            quantityInfo.put(order.getOrderDetails().get(i).getProductId(), order.getOrderDetails().get(i).getQuantity() );
        }

        // type inside the HttpEntity<here> must match with type that we pass to in as a first parameter
        // in here
        HttpEntity<HashMap<Long, Integer>> quantity = new HttpEntity(quantityInfo, httpHeaders);


        HashMap result = restTemplate.postForObject("http://localhost:8080/payment-service/payment/checkout",request, HashMap.class);

        System.out.println("this is before product call" + request.toString());

        if (result.get("valid").equals("true") && result.get("amount").equals("true")){
            //String checking = restTemplate.postForObject("http://localhost:8080/product-service/product/updateQuantity", quantity, String.class);
            //System.out.println("this is order service " + checking);


            //if(checking.equals("true")) {
//                itemDetailService.updateItemAfterPayment(order.getOrderDetails());
                return orderRepository.save(order);
            //}
            //else
                //throw new Exception("Not enough item in stock");
        }else if (result.get("valid").equals("false")){
            throw new Exception("You card information is not valid");
        }else {
            throw new Exception("You don't have enough amount in your cart to complete the purchase");
        }
    }

    
//    public Orders placeOrder(Orders order , HashMap<String, String> orderInfo) throws Exception {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        //HashMap<String , String> orderInfo = new HashMap<>();
//        HashMap<Long, Integer> quantityInfo = new HashMap<>();
//
////        orderInfo.put("cardNumber" , order.);
////        orderInfo.put("nameOnCard" , "ali ansari");
////        orderInfo.put("securityDigit" , "123");
////        orderInfo.put("expirationDate" , "2020-08-11"); // Localdate -> String
////        orderInfo.put("amount" , "" + order.getAmount());
//        HttpEntity<HashMap<String , String >> request = new HttpEntity<>(orderInfo, httpHeaders);
//
//        int size = order.getOrderDetails().size();
//        for (int i = 0; i < size; i++) {
//            quantityInfo.put(order.getOrderDetails().get(i).getProductId(), order.getOrderDetails().get(i).getQuantity() );
//        }
//
//        // type inside the HttpEntity<here> must match with type that we pass to in as a first parameter
//        // in here "listOfQuantity"
//        HttpEntity<HashMap<Long, Integer>> quantity = new HttpEntity(quantityInfo, httpHeaders);
//
//
//        HashMap result = restTemplate.postForObject("http://localhost:8080/payment-service/payment/checkout",request, HashMap.class);
//
//        System.out.println("this is before product call" + request.toString());
//
//        if (result.get("valid").equals("true") && result.get("amount").equals("true")){
//            String checking = restTemplate.postForObject("http://localhost:8080/product-service/product/updateQuantity", quantity, String.class);
//            System.out.println("this is order service " + checking);
//
//            if(checking.equals("true"))
//                return orderRepository.save(order);
//            else
//                throw new Exception("Not enough item in stock");
//        }else if (result.get("valid").equals("false")){
//            throw new Exception("You card information is not valid");
//        }else {
//            throw new Exception("You don't have enough amount in your cart to complete the purchase");
//        }
//    }

    public List<Orders> selectAllOrders(){
        return orderRepository.findAll();
    }

    public List<Orders> selectAllOrdersForSpecificUser(Long userId){
        return orderRepository.selectAllOrdersOfSpecificUser(userId);
    }

    public List<Orders> selectOrdersWithAllOrderDetailForSpecificUser(Long userId){
        return orderRepository.findAllByUserId(userId);
    }
}
