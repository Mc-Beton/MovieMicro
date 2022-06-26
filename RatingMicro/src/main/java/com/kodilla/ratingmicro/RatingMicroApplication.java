package com.kodilla.ratingmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RatingMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingMicroApplication.class, args);
    }

}
