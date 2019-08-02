package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.controllers;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.service.NotificationSenderService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v01")
public class PlaceNotificationBuilderController {

	private NotificationSenderService notificationSenderService;

	public PlaceNotificationBuilderController(NotificationSenderService notificationSenderService) {
		this.notificationSenderService = notificationSenderService;
	}

	@PostMapping("/notifications/managers/review-new-place-request")
	public ResponseEntity receiveNewPlaceRequestManagerNotification(@RequestBody PlaceRequest placeRequest) {

		notificationSenderService.receiveNewPlaceRequestManagerNotification(placeRequest);

		return ResponseEntity.ok().body(placeRequest);
	}


	@PostMapping("/notifications/employees/reviewed-place-request")
	public ResponseEntity receiveReviewedPlaceRequestEmployeeNotification(@RequestBody PlaceRequest placeRequest) {

		notificationSenderService.receiveReviewedPlaceRequestEmployeeNotification(placeRequest);

		return ResponseEntity.ok().body(placeRequest);
	}




	@GetMapping("/notifications/images/{imagename}")
	public void getImageAsByteArray(HttpServletResponse response, @PathVariable String imagename) throws IOException {
		InputStream in = getClass().getResourceAsStream("/img/" + imagename);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		IOUtils.copy(in, response.getOutputStream());
	}

}
