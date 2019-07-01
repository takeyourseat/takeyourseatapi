package com.stefanini.internship.placemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException(String message) {
        super(message);
    }
}
