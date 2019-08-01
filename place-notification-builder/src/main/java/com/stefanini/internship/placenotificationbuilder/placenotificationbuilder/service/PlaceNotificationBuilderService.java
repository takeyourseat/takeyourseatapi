package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.service;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationAction;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlaceNotificationBuilderService {



	public NotificationBuilder convertManagerNotificationToJSON(PlaceRequest placeRequest) {

		NotificationBuilder notification = new NotificationBuilder();

		notification.setTitle("New Place Request For Approvement");

		String body = placeRequest.getUsername() +
				" requested place (" + placeRequest.getPlace().getCoordinateX() + placeRequest.getPlace().getCoordinateY() + ") " +
				" in office " + placeRequest.getPlace().getOffice().getNumber() + " on " + placeRequest.getDateOf();
		notification.setBody(body);

		notification.getActions().add(new NotificationAction("approve", "Approve" ));
		notification.getActions().add(new NotificationAction("decline", "Decline" ));

		Map<String, Object> map =  new HashMap<>();
		map.put("default", "requestedplace#PlaceRequest" + placeRequest.getId());
		map.put("approve", "requestedplace/" + placeRequest.getId() + "/approve");
		map.put("decline", "requestedplace/" + placeRequest.getId() + "/decline");
		notification.getData().put("actionLinks",map);

		notification.setImage("http://localhost:8088/api/v01/notifications/images/image.jpg");

		return notification;
	}

}
