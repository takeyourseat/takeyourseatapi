package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "place-notification-builder", url ="localhost:" )
public interface PlaceNotificationBuilderProxy {
}
