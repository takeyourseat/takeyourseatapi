package com.stefanini.internship.notificationserver.services;

import com.stefanini.internship.notificationserver.exceptions.UserIsNotPresentException;
import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;
import com.stefanini.internship.notificationserver.model.dto.PushAdapter;
import com.stefanini.internship.notificationserver.repository.SubscriptionRepository;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


@Service
public class PushNotificationService {

	private SubscriptionRepository subscriptionRepository;
	private PushService pushService;

	public PushNotificationService(SubscriptionRepository subscriptionRepository, PushService pushService) {
		this.subscriptionRepository = subscriptionRepository;
		this.pushService = pushService;
	}

	public void sendNotification(Notification notification) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
		pushService.send(notification);
	}

	public void getPayLoad(String username, String payload) throws GeneralSecurityException, InterruptedException, JoseException, ExecutionException, IOException {
		Notification notification = createNotification(username, payload);
		sendNotification(notification);
	}

	public Notification createNotification(String username, String payLoad) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

		//First we create subscription
		Optional<SubscriptionDao> usernameFromDB = getSubscriptionFromDB(username);
		PushAdapter subscription = new PushAdapter(usernameFromDB.get());

		return new Notification(subscription, "{\"notification\":"+payLoad+"}");
	}

	public Optional<SubscriptionDao> getSubscriptionFromDB(String username) {
		Optional<SubscriptionDao> byUsername = subscriptionRepository.findByUsername(username);

		if (!byUsername.isPresent()) {
			throw new UserIsNotPresentException(String.format("User \"%s\" is not present in database", username));
		}

		return byUsername;
	}
}
