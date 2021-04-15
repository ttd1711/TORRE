package com.dz.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dz.apigw.properties.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
@EnableScheduling
public class APIGWApplication {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().addShutdownHook(new ShutdownThread());
            System.out.println("[Main thread] Shutdown hook added");
        } catch (Throwable t) {
            System.out.println("[Main thread] Could not add Shutdown hook");
        }
        SpringApplication.run(APIGWApplication.class, args);
    }
}
