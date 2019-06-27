package com.stefanini.internship.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
=======
>>>>>>> bd1baf1c6002b51f68e8ad08cf6e2c2e5539f410

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

<<<<<<< HEAD
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

=======
>>>>>>> bd1baf1c6002b51f68e8ad08cf6e2c2e5539f410
}
