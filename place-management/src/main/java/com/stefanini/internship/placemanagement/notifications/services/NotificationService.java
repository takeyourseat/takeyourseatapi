package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.authorization.AuthorizationUtils;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private PlaceNotificationBuilderProxyFeign feignProxy;

	public NotificationService(PlaceNotificationBuilderProxyFeign feignProxy) {
		this.feignProxy = feignProxy;
	}

	public void sendPlaceManagementManagerNotification(PlaceRequest placeRequest) {

		feignProxy.receiveManagerNotification(placeRequest, AuthorizationUtils.getAuthToken());
	}
}
