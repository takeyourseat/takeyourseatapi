package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.authorization.AuthorizationUtils;
import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private PlaceNotificationBuilderProxyFeign feignProxy;

	public NotificationService(PlaceNotificationBuilderProxyFeign feignProxy) {
		this.feignProxy = feignProxy;
	}

	public void sendNewPlaceRequestManagerNotification(PlaceRequest placeRequest) {

		feignProxy.sendNewPlaceRequestManagerNotification(placeRequest, AuthorizationUtils.getAuthToken());
	}

	public void sendReviewedPlaceRequestEmployeeNotification(PlaceRequest placeRequest) {

		feignProxy.sendReviewedPlaceRequestEmployeeNotification(placeRequest, AuthorizationUtils.getAuthToken());
	}

	public void sendModifiedPlaceNotification(Place newPlace) {

		feignProxy.sendModifiedPlaceNotification(newPlace, AuthorizationUtils.getAuthToken());
	}

//	public ModifiedPlace compactToPlaceHolder(Place newPlace) {
//		ModifiedPlace modifiedPlace = new ModifiedPlace();
//		modifiedPlace.setPlace(newPlace);
//
//		return modifiedPlace;
//	}


}
