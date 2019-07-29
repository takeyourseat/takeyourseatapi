package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.notifications.models.RequestPlaceManagementManager;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlaceNotificationBuilderService {

	public static final String NOTIFICATIONBUILDER_API = "http://localhost:8086/api/v01/notifications";

	private RestTemplate restTemplate;
	private PlaceNotificationBuilderProxyFeign feignProxy;

	public PlaceNotificationBuilderService(PlaceNotificationBuilderProxyFeign feignProxy, RestTemplate restTemplate) {
		this.feignProxy = feignProxy;
		this.restTemplate = restTemplate;
	}


	public void sendRequestPlaceManagementManagerNotificationRest(RequestPlaceManagementManager object) {

		String requestURI = NOTIFICATIONBUILDER_API + "/manager";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		RequestPlaceManagementManager placeManagementManager = new RequestPlaceManagementManager();

		HttpEntity<RequestPlaceManagementManager> request = new HttpEntity<>(placeManagementManager, headers);

		ResponseEntity<RequestPlaceManagementManager> responseEntity =
				restTemplate.postForEntity(requestURI, request, RequestPlaceManagementManager.class);

		RequestPlaceManagementManager response = responseEntity.getBody();

	}

	public void sendRequestPlaceManagementManagerNotificationFeign(RequestPlaceManagementManager object) {

		RequestPlaceManagementManager response = feignProxy.receiveManagerNotification(object);

	}
}
