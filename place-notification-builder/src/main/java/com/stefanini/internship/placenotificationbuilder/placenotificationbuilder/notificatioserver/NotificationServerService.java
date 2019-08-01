package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificatioserver;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.utils.AuthorizationUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationServerService {

	private NotificationFeignProxy feignProxy;

	public NotificationServerService(NotificationFeignProxy feignProxy) {
		this.feignProxy = feignProxy;
	}

	public void sendManagerNotificationJSON(String reviewer, NotificationBuilder objectJSON) {

		feignProxy.receiveManagerNotificationJSON(reviewer, objectJSON, AuthorizationUtils.getAuthToken());
	}
}





