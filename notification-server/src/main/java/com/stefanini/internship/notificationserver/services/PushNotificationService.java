package com.stefanini.internship.notificationserver.services;

import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;
import com.stefanini.internship.notificationserver.model.dto.PushAdapter;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
public class PushNotificationService {

	private SubscriptionServiceImpl subscriptionService;
	private PushService pushService;

	public PushNotificationService(SubscriptionServiceImpl subscriptionService, PushService pushService) {
		this.subscriptionService = subscriptionService;
		this.pushService = pushService;
	}

	public void sendNotifications(List<Notification> notifications) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
		for (Notification notification : notifications) {
			pushService.send(notification);
		}
	}

	public void sendReceivedNotificationJSON(String reviewer, String objectJSON) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException, NoSuchFieldException {
		List<Notification> notifications = createNotifications(reviewer, objectJSON);
		sendNotifications(notifications);
	}

	public List<Notification> createNotifications(String username, String payLoad) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, NoSuchFieldException {

		List<SubscriptionDao> subscriptionsFromDB = subscriptionService.getSubscriptionsFromDB(username);
		List<PushAdapter> subscriptions = PushAdapter.from(subscriptionsFromDB);
		List<Notification> notifications = new ArrayList<>();

		for (PushAdapter subscription : subscriptions) {
			Notification notification = new Notification(subscription, "{\"notification\":"+payLoad+"}");
			 notifications.add(notification);
		}
		return notifications;
	}

}
