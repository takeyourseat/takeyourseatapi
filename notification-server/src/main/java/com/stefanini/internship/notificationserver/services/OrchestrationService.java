package com.stefanini.internship.notificationserver.services;

import nl.martijndwars.webpush.Notification;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrchestrationService {

	private PushNotificationService pushNotificationService;

	public OrchestrationService(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}

	public void sendReceivedNotificationJSON(String reviewer, String objectJSON) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException, NoSuchFieldException {
		List<Notification> notifications = pushNotificationService.createNotification(reviewer, objectJSON);
		pushNotificationService.sendNotifications(notifications);

	}
}

