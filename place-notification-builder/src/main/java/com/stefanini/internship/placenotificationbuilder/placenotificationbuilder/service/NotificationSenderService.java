package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.service;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.Place;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificatioserver.NotificationServerService;
import org.springframework.stereotype.Service;


@Service
public class NotificationSenderService {

	private PlaceNotificationBuilderService placeNotificationBuilderService;
	private NotificationServerService notificationServerService;

	public NotificationSenderService(PlaceNotificationBuilderService placeNotificationBuilderService, NotificationServerService notificationServerService) {
		this.placeNotificationBuilderService = placeNotificationBuilderService;
		this.notificationServerService = notificationServerService;
	}

	public void receiveNewPlaceRequestManagerNotification(PlaceRequest placeRequest) {

		NotificationBuilder managerNotificationJSON = placeNotificationBuilderService.convertNewPlaceRequestManagerNotificationToJSON(placeRequest);
		notificationServerService.sendNotificationJSON(placeRequest.getReviewer(),managerNotificationJSON);
	}

	public void receiveReviewedPlaceRequestEmployeeNotification(PlaceRequest placeRequest) {

		NotificationBuilder employeeNotificationJSON = placeNotificationBuilderService.convertReviewedPlaceRequestEmployeeNotificationToJSON(placeRequest);
		notificationServerService.sendNotificationJSON(placeRequest.getReviewer(),employeeNotificationJSON);
	}

	public void receiveModifiedPlaceNotification(Place newPlace) {

		NotificationBuilder userNotificationJSON = placeNotificationBuilderService.modifiedPlaceNotificationToJSON(newPlace);
		notificationServerService.sendNotificationJSON(newPlace.getUsername(), userNotificationJSON);
	}


}
