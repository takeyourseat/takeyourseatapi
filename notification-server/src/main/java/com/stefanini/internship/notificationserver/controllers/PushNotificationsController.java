package com.stefanini.internship.notificationserver.controllers;

import com.stefanini.internship.notificationserver.exceptions.PushNotificationsException;
import com.stefanini.internship.notificationserver.model.dao.Notification;
import com.stefanini.internship.notificationserver.model.dao.PushNotifications;
import com.stefanini.internship.notificationserver.services.PushNotificationsService;
import com.stefanini.internship.notificationserver.services.PushNotificationsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v01")
public class PushNotificationsController {

	private PushNotificationsService pushNotificationsService;

	public PushNotificationsController(PushNotificationsServiceImpl webPushNotificationsService) {
		this.pushNotificationsService = webPushNotificationsService;
	}

	@GetMapping("/get/notifications")
	public ResponseEntity retrieveAllPushNotifications() {
		List<PushNotifications> pushNotificationsList = pushNotificationsService.findAllPushNotifications();
		if (pushNotificationsList.isEmpty()) {
			throw new PushNotificationsException("List with WebPushNotifications is Empty");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(pushNotificationsList);
		}
	}


	@GetMapping("/get/notifications/{username}")
	public ResponseEntity getAllPushNotificationsById(@PathVariable String username) {
		Optional<PushNotifications> pushNotificationsByUsername = pushNotificationsService.findByUsername(username);
		if (!pushNotificationsByUsername.isPresent()) {
			throw new PushNotificationsException("No such username - " + username);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(pushNotificationsByUsername);
		}
	}

	@DeleteMapping("/get/notifications/{username}")
	public ResponseEntity<Optional<PushNotifications>> deletePushNotificationsById(@PathVariable String username) {
		Optional<PushNotifications> pushNotificationsByUsername = pushNotificationsService.deleteByUsername(username);
		if (!pushNotificationsByUsername.isPresent()) {
			throw new PushNotificationsException("No such username to delete - " + username);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(pushNotificationsByUsername);
		}

	}

	@PostMapping("/notifications")
	public ResponseEntity<Object> createNotificationSubscription(@RequestBody PushNotifications webPushNotification) {

		PushNotifications savedNotification = pushNotificationsService.save(webPushNotification);


		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedNotification.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PostMapping("/notifications/managers")
	public ResponseEntity receiveManagerNotificationJSON(Notification objectJSON) {

		return ResponseEntity.ok().body(objectJSON);

	}


}



