package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.controllers;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.RequestPlaceManagementManager;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.services.PlaceNotificationBuilderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v01")
public class ReceivePlaceNotificationBuilderController {

	private PlaceNotificationBuilderService placeNotificationBuilderService;


	public ReceivePlaceNotificationBuilderController(PlaceNotificationBuilderService placeNotificationBuilderService) {
		this.placeNotificationBuilderService = placeNotificationBuilderService;
	}

	@PostMapping("/notifications/manager")
	public RequestPlaceManagementManager receiveManagerNotification(@RequestBody RequestPlaceManagementManager managerNotification) {

		return managerNotification;
	}

}
