package com.stefanini.internship.notificationserver.services;

import nl.martijndwars.webpush.Notification;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationBuilderService {

	private PushNotificationService pushService;

	public NotificationBuilderService(PushNotificationService pushService) {
		this.pushService = pushService;
	}

	public void sendPayLoad(String reviewer, String objectJSON) throws GeneralSecurityException, InterruptedException, JoseException, ExecutionException, IOException {

		Notification notification = pushService.createNotification(reviewer, objectJSON);
		pushService.sendNotification(notification);
	}

//	public String notificationBuilderToJSON(NotificationBuilder objectJSON) {
//		String payload = "";
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			payload = mapper.writeValueAsString(objectJSON);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return payload;
//	}
}
