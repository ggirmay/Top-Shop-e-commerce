package com.financialAndReporting.financialAndReporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients

public class FinancialAndReportingApplication {


	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(FinancialAndReportingApplication.class, args);
	}

}
