package com.stefanini.internship.usermanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {


		SpringApplication.run(UserManagementApplication.class, args);

		log.error("Logging is working - Message logged at ERROR level {} - remove this lines from com.stefanini.internship.usermanagement.UserManagementApplication");
		log.warn("Logging is working -Message logged at WARN level {} - remove this lines from com.stefanini.internship.usermanagement.UserManagementApplication");
		log.info("Logging is working -Message logged at INFO level {} - remove this lines from com.stefanini.internship.usermanagement.UserManagementApplication");
		log.debug("Logging is working -Message logged at DEBUG level {} - remove this lines from com.stefanini.internship.usermanagement.UserManagementApplication");
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
