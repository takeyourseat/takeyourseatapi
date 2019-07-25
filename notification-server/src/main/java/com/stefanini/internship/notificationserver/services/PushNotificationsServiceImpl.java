package com.stefanini.internship.notificationserver.services;


import com.stefanini.internship.notificationserver.model.dto.PushNotifications;
import com.stefanini.internship.notificationserver.repository.PushNotificationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PushNotificationsServiceImpl implements PushNotificationsService {

	private PushNotificationsRepository pushNotificationsRepository;

	public PushNotificationsServiceImpl(PushNotificationsRepository pushNotificationsRepository) {
		this.pushNotificationsRepository = pushNotificationsRepository;
	}

	@Override
	public List<PushNotifications> findAllPushNotifications() {
		return pushNotificationsRepository.findAll();
	}

	@Override
	public Optional<PushNotifications> findByUsername(String username) {
		return pushNotificationsRepository.findByUsername(username);

	}

	@Override
	public Optional<PushNotifications> deleteByUsername(String username) {
		return pushNotificationsRepository.deleteByUsername(username);
	}

	@Override
	public void deleteById(Long id) {
		pushNotificationsRepository.deleteById(id);
	}

	public PushNotifications save(PushNotifications pushNotification) {
		 return pushNotificationsRepository.save(pushNotification);
	}

}
