package com.stefanini.internship.notificationserver.controllers;

import com.stefanini.internship.notificationserver.services.OrchestrationService;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/api/v01")
public class NotificationBuilderController {

	private OrchestrationService orchestrationService;

	public NotificationBuilderController(OrchestrationService orchestrationService) {
		this.orchestrationService = orchestrationService;
	}


	@PostMapping("/notifications/{reviewer}/managers")
	public ResponseEntity receiveManagerNotificationJSON(@PathVariable("reviewer") String reviewer,
														 @RequestBody String objectJSON) throws GeneralSecurityException, InterruptedException, JoseException, ExecutionException, IOException {
		orchestrationService.sendReceivedNotificationJSON(reviewer, objectJSON);

		return ResponseEntity.ok().body(objectJSON);

	}
}
