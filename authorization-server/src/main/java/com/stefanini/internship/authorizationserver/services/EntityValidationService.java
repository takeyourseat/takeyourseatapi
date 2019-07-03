package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntityValidationService {

    public void AssertValidResult(User sid, String expected) throws ResourceNotFoundException {
        if(sid == null) {
            RuntimeException exception = new ResourceNotFoundException("User " + expected + " could not be found");
            log.debug("User not found", exception);
            throw exception;
        }
    }

    public void AssertValidResult(DataType dataType, String classname){
        if(dataType == null) {
            RuntimeException exception = new ResourceNotFoundException("Class " + classname + " could not be found in the OWA database");
            log.debug("Class not found", exception);
            throw exception;
        }
    }
}
