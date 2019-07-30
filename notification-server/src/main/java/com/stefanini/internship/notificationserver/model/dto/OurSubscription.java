package com.stefanini.internship.notificationserver.model.dto;

import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class OurSubscription extends Subscription {


	public OurSubscription(String endpoint, Keys keys) {


		super(endpoint, keys);
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
	}

}

