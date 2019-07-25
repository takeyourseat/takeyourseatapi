package com.stefanini.internship.notificationserver.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PushNotificationsException extends RuntimeException {
	public PushNotificationsException(String message) {
		super(message);
	}
}
