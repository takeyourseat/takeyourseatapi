package com.stefanini.internship.notificationserver.model.dto;

import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class PushAdapter extends Subscription {

	private final SubscriptionDao subscriptionDao;

	public PushAdapter(SubscriptionDao subscriptionDao) {
		super();
		this.subscriptionDao = subscriptionDao;
		setProp();



	}

	private void setProp() {
		super.endpoint = this.subscriptionDao.getEndpoint();
		super.keys = new Keys(this.subscriptionDao.getKey(), this.subscriptionDao.getAuth());
	}
}
