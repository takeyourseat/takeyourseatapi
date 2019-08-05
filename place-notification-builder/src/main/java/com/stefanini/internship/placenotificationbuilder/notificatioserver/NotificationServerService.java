package com.stefanini.internship.placenotificationbuilder.notificatioserver;

import com.stefanini.internship.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.utils.AuthorizationUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationServerService {

	private NotificationFeignProxy feignProxy;

	public NotificationServerService(NotificationFeignProxy feignProxy) {
		this.feignProxy = feignProxy;
	}

	public void sendNotificationJSON(String receiver, NotificationBuilder objectJSON) {

		feignProxy.sendNotificationJSON(receiver, objectJSON, AuthorizationUtils.getAuthToken());
	}
}





