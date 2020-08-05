package com.shop.top.payment.payment.controller;

import com.shop.top.payment.payment.model.CreditCard;
import com.shop.top.payment.payment.service.MastercardService;
import com.shop.top.payment.payment.service.VisaService;
import com.shop.top.payment.payment.utils.Getters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private VisaService visaService;

    @Autowired
    private MastercardService mastercardService;

    public ResponseEntity<Boolean> checkout(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String , String> map = new HashMap<>();
        map.put("cardNumber" , "4351930605662860");
        map.put("nameOnCard" , "ali ansari");
        map.put("securityDigit" , "123");
        map.put("expirationDate" , "2020-08-11"); // Localdate -> String
        map.put("amount" , "500"); // Double -> String

        HttpEntity<HashMap<String , String >> request = new HttpEntity<HashMap<String, String>>(map , httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity("http://payment-service/payment/checkout" , request , Boolean.class);
    }

    @PostMapping( value = "/checkout")
    public ResponseEntity<?> checkout(@RequestBody HashMap<String, String> data){

        System.out.println("working");
        String cardNumber = Getters.extractString(data.get("cardNumber"));
        String nameOnCard = Getters.extractString(data.get("nameOnCard"));
        String securityDigit = Getters.extractString(data.get("securityDigit"));
        LocalDate expirationDate = Getters.extractDate(data.get("expirationDate"));
        Double amount = Double.valueOf(data.get("amount"));

        HashMap<String, Boolean> result = new HashMap<>();

        if(cardNumber.startsWith("" + CreditCard.VISA.value()))
            result = visaService.checkout(cardNumber, nameOnCard, securityDigit, expirationDate, amount);
        /*else if(cardNumber.startsWith("" + CreditCard.MASTERCARD.value()))
            result = mastercardService.checkout(cardNumber, nameOnCard, securityDigit, expirationDate, amount);*/

        return ResponseEntity.ok().body(result);
    }

}
