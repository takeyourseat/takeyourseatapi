package com.stefanini.internship.notificationserver.controllers;

import com.stefanini.internship.notificationserver.services.NotificationBuilderService;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
public class NotificationBuilderController {

	private NotificationBuilderService notificationBuilderService;

	public NotificationBuilderController(NotificationBuilderService notificationBuilderService) {
		this.notificationBuilderService = notificationBuilderService;
	}


	@PostMapping("/api/v01/notifications/{reviewer}/managers")
	public ResponseEntity receiveManagerNotificationJSON(@PathVariable("reviewer") String reviewer,
														 @RequestBody String objectJSON) throws GeneralSecurityException, InterruptedException, JoseException, ExecutionException, IOException {
		notificationBuilderService.sendPayLoad(reviewer,objectJSON);

		return ResponseEntity.ok().body(objectJSON);

	}
}
