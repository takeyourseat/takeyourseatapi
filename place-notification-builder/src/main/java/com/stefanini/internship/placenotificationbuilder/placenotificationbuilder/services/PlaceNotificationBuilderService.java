package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.services;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.RequestPlaceManagementManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlaceNotificationBuilderService {

	public static final String NOTIFICATIONBUILDER_API = "http://localhost:8088/api/v01/notifications";


	private RestTemplate restTemplate;

	public PlaceNotificationBuilderService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	public void sendRequestPlaceManagementManagerNotification(RequestPlaceManagementManager object) {

		String requestURI = NOTIFICATIONBUILDER_API+"/manager";

		ResponseEntity<RequestPlaceManagementManager> responseEntity =
				restTemplate.postForEntity(requestURI, RequestPlaceManagementManager.class, RequestPlaceManagementManager.class);

		responseEntity.getBody();
	}
}
