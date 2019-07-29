package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificatioserver;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


	private static final String NOTIFICATIONBUILDER_API = "http://localhost:8087/api/v01/notifications";

	private NotificationFeignProxy feignProxy;

	public NotificationService(NotificationFeignProxy feignProxy) {
		this.feignProxy = feignProxy;
	}

	public void sendManagerNotificationJSON(Notification objectJSON) {

		feignProxy.receiveManagerNotificationJSON(objectJSON);
	}
}





