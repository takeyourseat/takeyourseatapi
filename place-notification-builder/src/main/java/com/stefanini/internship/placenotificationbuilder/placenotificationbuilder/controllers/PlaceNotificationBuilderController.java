package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.controllers;

import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.NotificationBuilder;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.model.dto.PlaceRequest;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.notificatioserver.NotificationServerService;
import com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.service.PlaceNotificationBuilderService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v01")
public class PlaceNotificationBuilderController {

	private PlaceNotificationBuilderService placeNotificationBuilderService;
	private NotificationServerService notificationService;
	private ServletContext servletContext;

	public PlaceNotificationBuilderController(PlaceNotificationBuilderService placeNotificationBuilderService, NotificationServerService notificationService, ServletContext servletContext) {
		this.placeNotificationBuilderService = placeNotificationBuilderService;
		this.notificationService = notificationService;
		this.servletContext = servletContext;
	}


	@PostMapping("/notifications/managers")
	public ResponseEntity receiveManagerNotification(@RequestBody PlaceRequest managerNotification) {

		NotificationBuilder managerNotificationJSON = placeNotificationBuilderService.convertManagerNotificationToJSON(managerNotification);
		notificationService.sendManagerNotificationJSON(managerNotification.getReviewer(),managerNotificationJSON);

		return ResponseEntity.ok().body(managerNotification);
	}

	@GetMapping("/notifications/images/{imagename}")
	public void getImageAsByteArray(HttpServletResponse response, @PathVariable String imagename) throws IOException {
		InputStream in = getClass().getResourceAsStream("/img/" + imagename);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		IOUtils.copy(in, response.getOutputStream());
	}

}
