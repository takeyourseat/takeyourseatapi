package com.stefanini.internship.authorizationserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(){}

    public DuplicateUserException(String message){
        super(message);
    }
}
