package com.stefanini.internship.notificationserver.services;


import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

	List<SubscriptionDao> findAllPushNotifications();

	Optional<SubscriptionDao> findByUsername(String username);

	Optional<SubscriptionDao> deleteByUsername(String username);

	SubscriptionDao save(SubscriptionDao pushNotification);


	void deleteById(Long id);

}
