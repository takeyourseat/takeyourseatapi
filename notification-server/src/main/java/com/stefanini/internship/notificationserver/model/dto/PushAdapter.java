package com.stefanini.internship.notificationserver.model.dto;

import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/*
			To understand how works this API Subscription check this out:

https://github.com/web-push-libs/webpush-java/wiki/Usage-Example#serverside---parse-the-input

 */

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

	public static List<PushAdapter> from (List<SubscriptionDao> subscriptions) {

		List<PushAdapter> pushAdapters = new ArrayList<>();

		for(SubscriptionDao subscription: subscriptions ) {
			PushAdapter adapter = new PushAdapter(subscription);
			pushAdapters.add(adapter);
		}
		return pushAdapters;
	}
}
