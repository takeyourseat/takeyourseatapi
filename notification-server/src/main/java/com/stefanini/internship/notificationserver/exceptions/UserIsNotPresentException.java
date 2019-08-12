package com.stefanini.internship.notificationserver.exceptions;

public class UserIsNotPresentException extends RuntimeException {
	public UserIsNotPresentException(String message) {
		super(message);
	}
}

