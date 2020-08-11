package com.shop.top.payment.payment.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/")
    public String login(){
        return "";
    }

}
