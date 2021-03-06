package com.stefanini.internship.notificationserver.configs;

import nl.martijndwars.webpush.PushService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.GeneralSecurityException;
import java.security.Security;

@Configuration
public class VAPIDPushServiceBean {

	private static final String VAPID_PUBLIC_KEY = "BIo4B1bsWsS3fDQZJjFo3k_M9C5sMm929H5EJMbqcYicjCiseaYeCDsE6dIB5NNw4u6rlW8YUWhs-evYAwa2mOM";
	private static final String VAPID_PRIVATE_KEY = "dw1-Fz9_bD1aX9OAZ8uRt8c5p-CNNczirkGBiMYTUVM";
	private static final String VAPID_SUBJECT = "";

	@Bean
	public PushService pushService() throws GeneralSecurityException {
		//In case if we'll use encountering and will cover this exception: "InvalidKeyException: Not an EC key: ECDH"
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		return new PushService(VAPID_PUBLIC_KEY, VAPID_PRIVATE_KEY, VAPID_SUBJECT);
	}


}
