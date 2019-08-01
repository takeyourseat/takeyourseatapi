package com.stefanini.internship.placemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NotAvailableException extends RuntimeException {
    public NotAvailableException(String message) {
        super(message);
    }
}
