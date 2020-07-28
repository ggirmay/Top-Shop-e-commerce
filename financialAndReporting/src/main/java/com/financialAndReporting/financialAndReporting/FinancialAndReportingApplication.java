package com.financialAndReporting.financialAndReporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class FinancialAndReportingApplication {
	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@GetMapping(value = "/category")
	public ResponseEntity<String> test(){
		String str = restTemplate.getForObject("http://localhost:8080/shopping-cart-service/test", String.class);
		return ResponseEntity.ok().body("Now you are connected to another service:  " + " " + str);
	}

	public static void main(String[] args) {
		SpringApplication.run(FinancialAndReportingApplication.class, args);
	}

}
