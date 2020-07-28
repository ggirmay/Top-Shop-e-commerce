package com.shop.top.productservice.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ProductServiceApplication {

    @GetMapping(value = "/products")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("---- I am product ----");
    }
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
