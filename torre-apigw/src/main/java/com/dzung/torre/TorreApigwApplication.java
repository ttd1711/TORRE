package com.dzung.torre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TorreApigwApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorreApigwApplication.class, args);
	}

}
