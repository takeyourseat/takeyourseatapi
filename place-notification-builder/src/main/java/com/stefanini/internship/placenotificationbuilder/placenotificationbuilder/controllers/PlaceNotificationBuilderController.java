package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.controllers;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificatioserver.NotificationServerService;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.service.PlaceNotificationBuilderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v01")
public class PlaceNotificationBuilderController {

	private PlaceNotificationBuilderService placeNotificationBuilderService;
	private NotificationServerService notificationService;

	public PlaceNotificationBuilderController(PlaceNotificationBuilderService placeNotificationBuilderService, NotificationServerService notificationService) {
		this.placeNotificationBuilderService = placeNotificationBuilderService;
		this.notificationService = notificationService;
	}


	@PostMapping("/notifications/managers")
	public ResponseEntity receiveManagerNotification(@RequestBody PlaceRequest managerNotification) {

		NotificationBuilder managerNotificationJSON = placeNotificationBuilderService.convertManagerNotificationToJSON(managerNotification);
		notificationService.sendManagerNotificationJSON(managerNotification.getReviewer(),managerNotificationJSON);

		return ResponseEntity.ok().body(managerNotification);
	}

}
