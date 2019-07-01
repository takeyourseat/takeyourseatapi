package com.stefanini.internship.oauthserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.client.RestTemplate;
@Slf4j
@SpringBootApplication
@EnableAuthorizationServer
public class OauthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthServerApplication.class, args);

		log.error("Logging is working - Message logged at ERROR level {} - remove this lines from com.stefanini.internship.oauthserver.OauthServerApplication");
		log.warn("Logging is working -Message logged at WARN level {} - remove this lines from com.stefanini.internship.oauthserver.OauthServerApplication");
		log.info("Logging is working -Message logged at INFO level {} - remove this lines from com.stefanini.internship.oauthserver.OauthServerApplication");
		log.debug("Logging is working -Message logged at DEBUG level {} - remove this lines from com.stefanini.internship.oauthserver.OauthServerApplication");
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
