package com.stefanini.internship.notificationserver.services;

import com.stefanini.internship.notificationserver.model.dto.NotificationBuilder;
import com.stefanini.internship.notificationserver.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;


@Service
public class PushService {

	private SubscriptionRepository subscriptionRepository;

	public PushService(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

	public Object createNotification(NotificationBuilder payLoad) {


		return payLoad;

	}


	public void createSubscription() {


	}

}
