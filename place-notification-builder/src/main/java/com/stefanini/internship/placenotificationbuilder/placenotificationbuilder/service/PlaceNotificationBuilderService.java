package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.service;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.excpetions.ResourceInvalidStateException;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationAction;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlaceNotificationBuilderService {





	public NotificationBuilder convertNewPlaceRequestManagerNotificationToJSON(PlaceRequest placeRequest) {

		NotificationBuilder notification = new NotificationBuilder();

		notification.setTitle("New Place Request For Approvement");

		String body = placeRequest.getUsername() +
				" requested place (" + placeRequest.getPlace().getCoordinateX() +", "+ placeRequest.getPlace().getCoordinateY() + ") " +
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

	public NotificationBuilder convertReviewedPlaceRequestEmployeeNotificationToJSON(PlaceRequest placeRequest) {
		if(placeRequest.getApproved() == null) {
			throw new ResourceInvalidStateException("Approved can't be null");
		}else if(placeRequest.getApproved().equals(false)) {
			return declinedPlaceRequestEmployeeNotification(placeRequest);
		}
		return acceptedPlaceRequestEmployeeNotification(placeRequest);
	}

	public NotificationBuilder acceptedPlaceRequestEmployeeNotification(PlaceRequest placeRequest) {

		NotificationBuilder notification = new NotificationBuilder();

		notification.setTitle("Your placerequest has been accepted");

		String body = "Your request for place" + "(" + placeRequest.getPlace().getCoordinateX() +", "+ placeRequest.getPlace().getCoordinateY() + ")" +
				" in office " + placeRequest.getPlace().getOffice().getNumber() +
				" has been accepted by " + placeRequest.getReviewer() +
				" on " + placeRequest.getDateOf();
		notification.setBody(body);

		Map<String, Object> map =  new HashMap<>();
		map.put("default", "/");
		notification.getData().put("actionLinks",map);

		return notification;
	}

	public NotificationBuilder declinedPlaceRequestEmployeeNotification(PlaceRequest placeRequest) {

		NotificationBuilder notification = new NotificationBuilder();

		notification.setTitle("Your placerequest has been declined");

		String body = "Your request for place" + "(" + placeRequest.getPlace().getCoordinateX() +", "+ placeRequest.getPlace().getCoordinateY() + ")" +
				" in office " + placeRequest.getPlace().getOffice().getNumber() +
				" has been declined by " + placeRequest.getReviewer() +
				" on " + placeRequest.getDateOf();
		notification.setBody(body);

		Map<String, Object> map =  new HashMap<>();
		map.put("default", "/");
		notification.getData().put("actionLinks",map);

		return notification;
	}
}
