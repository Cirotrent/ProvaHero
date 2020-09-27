package com.provahero.provahero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProvaheroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvaheroApplication.class, args);
	}

}
