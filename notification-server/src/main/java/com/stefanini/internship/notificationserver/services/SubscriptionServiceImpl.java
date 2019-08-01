package com.stefanini.internship.notificationserver.services;


import com.stefanini.internship.notificationserver.model.dao.SubscriptionDao;
import com.stefanini.internship.notificationserver.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	private SubscriptionRepository pushNotificationsRepository;

	public SubscriptionServiceImpl(SubscriptionRepository pushNotificationsRepository) {
		this.pushNotificationsRepository = pushNotificationsRepository;
	}

	@Override
	public List<SubscriptionDao> findAllPushNotifications() {
		return pushNotificationsRepository.findAll();
	}

	@Override
	public Optional<SubscriptionDao> findByUsername(String username) {
		return pushNotificationsRepository.findByUsername(username);
	}

	@Override
	public List<SubscriptionDao> getSubscriptionsFromDB(String username) {
		return  pushNotificationsRepository.findAllByUsername(username);
	}

	@Override
	public Optional<SubscriptionDao> deleteByUsername(String username) {
		return pushNotificationsRepository.deleteByUsername(username);
	}

	@Override
	public void deleteById(Long id) {
		pushNotificationsRepository.deleteById(id);
	}

	public SubscriptionDao save(SubscriptionDao pushNotification) {
		return pushNotificationsRepository.save(pushNotification);
	}

}
