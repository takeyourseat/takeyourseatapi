package com.stefanini.internship.notificationserver.services;

import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@Service
public class OrchestrationService {

	private PushNotificationService pushNotificationService;

	public OrchestrationService(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}

	public void sendReceivedNotificationJSON(String reviewer, String objectJSON) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
		pushNotificationService.getPayLoad(reviewer, objectJSON);


	}
}
