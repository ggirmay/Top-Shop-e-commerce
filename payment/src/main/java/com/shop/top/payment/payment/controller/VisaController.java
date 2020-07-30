package com.shop.top.payment.payment.controller;

import com.shop.top.payment.payment.model.visa.VisaTransaction;
import com.shop.top.payment.payment.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/visa-api")
public class VisaController {

    @Autowired
    private VisaService visaService;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<VisaTransaction> checkout(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
