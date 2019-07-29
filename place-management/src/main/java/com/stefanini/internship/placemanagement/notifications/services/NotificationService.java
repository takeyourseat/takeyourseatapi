package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private static final String NOTIFICATIONBUILDER_API = "http://localhost:8088/api/v01/notifications";

	private PlaceNotificationBuilderProxyFeign feignProxy;

	public NotificationService(PlaceNotificationBuilderProxyFeign feignProxy) {
		this.feignProxy = feignProxy;
	}

	public void sendRequestPlaceManagementManagerNotificationFeign(PlaceRequest object) {

		feignProxy.receiveManagerNotification(object);
	}
}
