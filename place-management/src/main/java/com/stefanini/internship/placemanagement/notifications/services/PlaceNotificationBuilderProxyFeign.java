package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import com.stefanini.internship.placemanagement.notifications.model.dto.ModifiedPlace;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "place-notification-builder", url ="localhost:8088" )
public interface PlaceNotificationBuilderProxyFeign {

	@PostMapping("/api/v01/notifications/managers/review-new-place-request")
	PlaceRequest sendNewPlaceRequestManagerNotification(@RequestBody PlaceRequest managerNotification,
														@RequestHeader("Authorization") String token);

	@PostMapping("/api/v01/notifications/employees/reviewed-place-request")
	ResponseEntity sendReviewedPlaceRequestEmployeeNotification(@RequestBody PlaceRequest employeeNotification,
																@RequestHeader("Authorization") String token);

	@PostMapping("/api/v01/notifications/users/modified-place-request")
	ResponseEntity sendModifiedPlaceNotification(@RequestBody Place newPlace,
												 @RequestHeader("Authorization") String token);
}
