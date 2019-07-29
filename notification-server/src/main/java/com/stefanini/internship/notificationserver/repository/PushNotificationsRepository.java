package com.stefanini.internship.notificationserver.repository;


import com.stefanini.internship.notificationserver.model.dao.PushNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PushNotificationsRepository extends JpaRepository<PushNotifications, Long> {

	Optional<PushNotifications> findByUsername(String username);

	Optional<PushNotifications> deleteByUsername(String username);


}
