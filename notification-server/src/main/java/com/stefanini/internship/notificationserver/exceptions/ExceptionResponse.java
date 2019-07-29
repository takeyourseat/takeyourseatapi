package com.stefanini.internship.notificationserver.exceptions;

import java.util.Date;

/*
This class will work as a "model" for all exceptions responses which well be able to configure in a way we want. Check CustomizedResponseEntityExceptionHandler class.
 */

public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String details;

	public ExceptionResponse() {
	}

	public ExceptionResponse(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
