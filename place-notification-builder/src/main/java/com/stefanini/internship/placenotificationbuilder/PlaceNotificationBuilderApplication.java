package com.stefanini.internship.placenotificationbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlaceNotificationBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceNotificationBuilderApplication.class, args);
    }


}
