package com.shop.top.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class ShoppingcartApplication {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }




//    @GetMapping("/test")
//    String testMethod(){
//        String result = restTemplate.getForObject("http://localhost:8080/product-service-service/products", String.class);
//        return "this is shopping cart test--- " + result;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartApplication.class, args);
    }

}
