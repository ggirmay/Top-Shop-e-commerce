package com.shop.top.payment.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class PaymentService {

    @GetMapping("")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
