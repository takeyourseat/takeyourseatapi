package com.stefanini.internship.notificationserver.controllers;

import com.stefanini.internship.notificationserver.services.PushNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/v01")
public class NotificationBuilderController {

	private PushNotificationService pushNotificationService;

	public NotificationBuilderController(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}


	@PostMapping("/notifications/{receiver}")
	public ResponseEntity receiveManagerNotificationJSON(@PathVariable("receiver") String receiver,
														 @RequestBody String objectJSON) throws GeneralSecurityException, InterruptedException, JoseException, ExecutionException, IOException, NoSuchFieldException {
		log.info(String.format("Send notification to %s ", receiver));
		pushNotificationService.sendReceivedNotificationJSON(receiver, objectJSON);

		return ResponseEntity.ok().body(objectJSON);

	}
}
