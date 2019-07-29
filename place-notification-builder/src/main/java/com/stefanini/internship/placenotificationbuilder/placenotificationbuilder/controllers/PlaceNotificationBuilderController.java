package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.controllers;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.Notification;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificatioserver.NotificationService;
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
	private NotificationService notificationService;

	public PlaceNotificationBuilderController(PlaceNotificationBuilderService placeNotificationBuilderService, NotificationService notificationService) {
		this.placeNotificationBuilderService = placeNotificationBuilderService;
		this.notificationService = notificationService;
	}


	@PostMapping("/notifications/manager")
	public ResponseEntity receiveManagerNotification(@RequestBody PlaceRequest managerNotification) {

		Notification managerNotificationJSON = placeNotificationBuilderService.convertManagerNotificationtoJSON(managerNotification);
		notificationService.sendManagerNotificationJSON(managerNotificationJSON);




		return ResponseEntity.ok().body(managerNotification);
	}

}
