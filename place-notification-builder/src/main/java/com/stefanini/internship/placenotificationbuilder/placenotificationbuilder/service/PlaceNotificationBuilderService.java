package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.service;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import org.springframework.stereotype.Service;

@Service
public class PlaceNotificationBuilderService {

	public NotificationBuilder convertManagerNotificationToJSON(PlaceRequest placeRequest) {

		NotificationBuilder notification = new NotificationBuilder();
		notification.setTitle("New Place Request For Approvement");
		String body = placeRequest.getUsername() +
				" requested place (" + placeRequest.getPlace().getCoordinateX() + placeRequest.getPlace().getCoordinateY() + ") " +
				" in office " + placeRequest.getPlace().getOffice().getNumber() + " on " + placeRequest.getDateOf();
		notification.setBody(body);

		return notification;
	}

}
