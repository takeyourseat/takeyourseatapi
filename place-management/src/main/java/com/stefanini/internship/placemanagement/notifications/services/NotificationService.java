package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

	private static final String NOTIFICATIONBUILDER_API = "http://localhost:8088/api/v01/notifications";

	private RestTemplate restTemplate;
	private PlaceNotificationBuilderProxyFeign feignProxy;

	public NotificationService(PlaceNotificationBuilderProxyFeign feignProxy, RestTemplate restTemplate) {
		this.feignProxy = feignProxy;
		this.restTemplate = restTemplate;
	}

	public void sendRequestPlaceManagementManagerNotificationRest(PlaceRequest object) {

		String requestURI = NOTIFICATIONBUILDER_API + "/manager";

		HttpEntity<PlaceRequest> request = new HttpEntity<>(object);

		ResponseEntity<PlaceRequest> responseEntity =
				restTemplate.postForEntity(requestURI, request, PlaceRequest.class);
		System.out.println(responseEntity.getStatusCode());
	}

	public void sendRequestPlaceManagementManagerNotificationFeign(PlaceRequest object) {

		PlaceRequest response = feignProxy.receiveManagerNotification(object);
		System.out.println(response);

	}
}
