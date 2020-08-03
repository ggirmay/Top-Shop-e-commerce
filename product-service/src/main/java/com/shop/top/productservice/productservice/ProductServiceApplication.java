package com.shop.top.productservice.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import javax.ws.rs.GET;
//import javax.xml.ws.Response;

=======
>>>>>>> c311884b89d67348cf6c6aab78db0c01b78bad5c
@SpringBootApplication
@RestController
@EnableEurekaClient
public class ProductServiceApplication {

    @GetMapping(value = "/products")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("its working");
    }
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
