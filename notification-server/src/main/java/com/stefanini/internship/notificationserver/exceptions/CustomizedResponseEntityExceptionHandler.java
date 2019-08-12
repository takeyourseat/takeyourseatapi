package com.stefanini.internship.notificationserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/*
This class offers to us possibility to customize responses for any exceptions we want using class "ExceptionResponse" as a "model"
 */
//@ControllerAdvice  //this configuration will be applied to all RestControllers
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	Configured Exception created for showing the status 500 when consumer introduced data which not exist in our logic
	 */

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	Configured WebPushNotificationsException created for showing the status 404 when consumer introduced data which not exist in our logic
	 */

	@ExceptionHandler(SubscriptionException.class)
	public final ResponseEntity<Object> handleWebPushNotificationsException(SubscriptionException ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
