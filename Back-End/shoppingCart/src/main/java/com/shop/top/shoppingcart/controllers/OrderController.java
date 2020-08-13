package com.shop.top.shoppingcart.controllers;

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
    @PostMapping(value = "/placeorder" /*, consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE , MediaType.MULTIPART_FORM_DATA_VALUE }*/)
    public Orders placeOrder(/*@RequestPart("order") Orders order , @RequestPart("card-info") PaymentInformation paymentInfo*/ @RequestBody PlaceOrderDTO data ) throws NumberFormatException, ParseException{
    	
//    	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
//    	Date d = df.parse(String.valueOf(data.get("createdDate")));
//    	
//    	Orders order = new Orders( Long.valueOf(String.valueOf(data.get("userId"))) , (String) data.get("userName") ,
//    			 d , String.valueOf(data.get("status")) , Float.valueOf(String.valueOf(data.get("amount"))));
//    
//    	
//    	ArrayList<OrderDetail> orderDetails = (ArrayList) data.get("orderDetails");
//    	order.setOrderDetails(orderDetails);
//    	System.out.println(orderDetails);
//    	
//    	HashMap<String, String> orderInfo = new HashMap<>();
//    	orderInfo.put("cardNumber" , String.valueOf(data.get("cardNumber")));
//    	orderInfo.put("nameOnCard" , String.valueOf(data.get("nameOnCard")));
//    	orderInfo.put("securityDigit" , String.valueOf(data.get("secDigit")));
//    	orderInfo.put("expirationDate" , String.valueOf(data.get("expDate"))); // Localdate -> String
//    	orderInfo.put("amount" , "" + String.valueOf(order.getAmount()));
//    	
    	Orders order = data.getOrder();
    	PaymentInformation paymentInfo = data.getCard();
    	
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
