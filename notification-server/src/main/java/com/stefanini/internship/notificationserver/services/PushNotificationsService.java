package com.stefanini.internship.notificationserver.services;


import com.stefanini.internship.notificationserver.model.dto.PushNotifications;

import java.util.List;
import java.util.Optional;

public interface PushNotificationsService {

	List<PushNotifications> findAllPushNotifications();

	Optional<PushNotifications> findByUsername(String username);

	Optional<PushNotifications> deleteByUsername(String username);

	PushNotifications save(PushNotifications pushNotification);


	void deleteById(Long id);

}
