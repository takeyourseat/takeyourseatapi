package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "place-notification-builder", url ="localhost:8088" )
public interface PlaceNotificationBuilderProxyFeign {

	@PostMapping("/api/v01/notifications/managers")
	PlaceRequest receiveManagerNotification(@RequestBody PlaceRequest managerNotification,
											@RequestHeader("Authorization") String token);
}
