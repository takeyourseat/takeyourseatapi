package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EntityValidationService {
    private final static Logger logger = Logger.getLogger(EntityValidationService.class);

    public void AssertValidResult(User sid, String expected) throws ResourceNotFoundException {
        if(sid == null) {
            RuntimeException exception = new ResourceNotFoundException("User " + expected + " could not be found");
            logger.debug("User not found", exception);
            throw exception;
        }
    }

    public void AssertValidResult(DataType dataType, String classname){
        if(dataType == null) {
            RuntimeException exception = new ResourceNotFoundException("Class " + classname + " could not be found in the OWA database");
            logger.debug("Class not found", exception);
            throw exception;
        }
    }
}
