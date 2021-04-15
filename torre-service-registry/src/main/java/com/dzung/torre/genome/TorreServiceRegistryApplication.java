package com.dzung.torre.genome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TorreServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorreServiceRegistryApplication.class, args);
	}

}
