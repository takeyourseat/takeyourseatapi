package com.stefanini.internship.placemanagement.notifications.services;

import com.stefanini.internship.placemanagement.notifications.models.RequestPlaceManagementManager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "place-notification-builder", url ="localhost:8088" )
public interface PlaceNotificationBuilderProxyFeign {

	@PostMapping("/notifications/manager")
	RequestPlaceManagementManager receiveManagerNotification(@RequestBody RequestPlaceManagementManager managerNotification);
}
