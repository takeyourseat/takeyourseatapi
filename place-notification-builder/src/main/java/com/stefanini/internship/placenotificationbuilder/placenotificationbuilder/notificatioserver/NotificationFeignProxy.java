package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificatioserver;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "place-notification-builder", url ="localhost:8087" )
public interface NotificationFeignProxy {

	@PostMapping("/api/v01/notifications/managers")
	 ResponseEntity receiveManagerNotificationJSON(Notification objectJSON);

}
