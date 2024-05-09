package com.weathertask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherTaskApplication.class, args);
    }

}
