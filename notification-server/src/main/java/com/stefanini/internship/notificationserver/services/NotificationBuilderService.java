package com.stefanini.internship.notificationserver.services;

import com.stefanini.internship.notificationserver.model.dto.NotificationBuilder;
import org.springframework.stereotype.Service;

@Service
public class NotificationBuilderService {

	private PushService pushService;

	public NotificationBuilderService(PushService pushService) {
		this.pushService = pushService;
	}

	public NotificationBuilder sendPayLoad(NotificationBuilder objectJSON) {

		return (NotificationBuilder) pushService.createNotification(objectJSON);

	}
}
