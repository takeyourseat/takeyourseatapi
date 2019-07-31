package com.stefanini.internship.notificationserver.controllers;

import com.stefanini.internship.notificationserver.services.NotificationBuilderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public class NotificationBuilderController {

	private NotificationBuilderService notificationBuilderService;

	public NotificationBuilderController(NotificationBuilderService notificationBuilderService) {
		this.notificationBuilderService = notificationBuilderService;
	}


	@PostMapping("/notifications/{reviewer}/managers")
	public ResponseEntity receiveManagerNotificationJSON(@PathVariable("reviewer") String reviewer,
														 @RequestBody String objectJSON) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
		notificationBuilderService.sendPayLoad(reviewer,objectJSON);

		return ResponseEntity.ok().body(objectJSON);

	}
}
