package com.getway.APIgetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApIgetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApIgetwayApplication.class, args);
	}

}
