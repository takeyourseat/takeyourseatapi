package com.stefanini.internship.oauthserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class SpringOneController {

	private final RestTemplate restTemplate;

	public SpringOneController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(value = "/springone")
	public String springOne () {
		log.info("Before Calling The Server");
		String response = restTemplate.getForObject("http://localhost:8082/server", String.class);
		log.info("After Calling The Server");

		String name ="kk";
		if(name.length() == 2) {
			throw  new RuntimeException("Opps exception has occured");
		}

		return "SPRINGoNE [" + response + "]";
	}
}
