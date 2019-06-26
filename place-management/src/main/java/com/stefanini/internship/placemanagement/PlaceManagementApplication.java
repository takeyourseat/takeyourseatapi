package com.stefanini.internship.placemanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@Slf4j
@SpringBootApplication
public class PlaceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaceManagementApplication.class, args);

		log.error("Logging is working - Message logged at ERROR level {} - remove this lines from com.stefanini.internship.placemanagement.PlaceManagementApplication");
		log.warn("Logging is working -Message logged at WARN level {} - remove this lines from com.stefanini.internship.placemanagement.PlaceManagementApplication");
		log.info("Logging is working -Message logged at INFO level {} - remove this lines from com.stefanini.internship.placemanagement.PlaceManagementApplication");
		log.debug("Logging is working -Message logged at DEBUG level {} - remove this lines from com.stefanini.internship.placemanagement.PlaceManagementApplication");
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
