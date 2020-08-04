package com.shop.top.payment.payment.controller;

import com.shop.top.payment.payment.model.CreditCard;
import com.shop.top.payment.payment.service.MastercardService;
import com.shop.top.payment.payment.service.VisaService;
import com.shop.top.payment.payment.utils.Getters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private VisaService visaService;

    @Autowired
    private MastercardService mastercardService;
    
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody HashMap<String, ?> data){
        String cardNumber = Getters.extractString(data.get("cardNumber"));
        String nameOnCard = Getters.extractString(data.get("nameOnCard"));
        String securityDigit = Getters.extractString(data.get("securityDigit"));
        LocalDate expirationDate = Getters.extractDate(data.get("expirationDate"));
        Double amount = (Double) data.get("amount");


        if(cardNumber.startsWith("" + CreditCard.VISA.value()))
            visaService.checkout(cardNumber, nameOnCard, securityDigit, expirationDate, amount);
        else if(cardNumber.startsWith("" + CreditCard.MASTERCARD.value()))
            mastercardService.checkout(cardNumber, nameOnCard, securityDigit, expirationDate, amount);

        return ResponseEntity.ok().body(true);
    }

}
