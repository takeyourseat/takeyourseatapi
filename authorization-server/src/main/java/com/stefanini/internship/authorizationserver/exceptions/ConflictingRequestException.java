package com.stefanini.internship.authorizationserver.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@NoArgsConstructor
public class ConflictingRequestException extends RuntimeException {

    public ConflictingRequestException(String message) {
        super(message);
    }
}
