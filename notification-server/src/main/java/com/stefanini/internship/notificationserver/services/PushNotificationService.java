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

	public void  sendNotification(Notification notification) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
		pushService.send(notification);
	}

	public Optional<SubscriptionDao> getDataFromDB(String username) {
		Optional<SubscriptionDao> byUsername = subscriptionRepository.findByUsername(username);
		if (!byUsername.isPresent()) {
			throw new UserIsNotPresentException("User is not present in DB");
		}
		return byUsername;
	}

	public Notification createNotification(String username, String payLoad) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {

		Optional<SubscriptionDao> object = getDataFromDB(username);
		PushAdapter adapter = new PushAdapter(object.get());


		Notification notification = new Notification(adapter, payLoad);

		return notification;
	}






}
