package com.shop.top.payment.payment.controller;

import com.shop.top.payment.payment.model.mastercard.MastercardTransaction;
import com.shop.top.payment.payment.model.visa.VisaTransaction;
import com.shop.top.payment.payment.service.MastercardService;
import com.shop.top.payment.payment.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mastercard-api")
public class MastercardController {

    @Autowired
    private MastercardService mastercardService;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<MastercardTransaction> checkout(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
