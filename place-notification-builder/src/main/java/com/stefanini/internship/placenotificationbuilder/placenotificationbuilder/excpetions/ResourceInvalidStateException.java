package com.stefanini.internship.placenotificationbuilder.placenotificationbuilder.excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceInvalidStateException extends RuntimeException {

	public ResourceInvalidStateException(String s) {
		super(s);
	}
}
