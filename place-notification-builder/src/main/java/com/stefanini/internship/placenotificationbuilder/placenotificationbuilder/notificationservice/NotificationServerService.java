package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificationservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServerService {

	private RestTemplate restTemplate;

	public NotificationServerService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}




}
