package com.stefanini.internship.authorizationserver.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class IlleagalRequestDataException extends RuntimeException {
    public IlleagalRequestDataException(String message){
        super(message);
    }
}
