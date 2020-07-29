package com.top.shop.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class UserApplication {

    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://localhost:8080/financialAndReporting-service/test";
    
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @GetMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(){
        String message = restTemplate.getForObject(url,String.class);
        return ResponseEntity.ok().body("Hello world from User micro Service --- " + message);
    }
}
