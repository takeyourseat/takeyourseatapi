package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EntityValidationService {

    public void AssertValidResult(User sid, String expected){
        if(sid == null)
           throw new ResourceNotFoundException("User "+expected+" could not be found");
    }

    public void AssertValidResult(DataType dataType, String classname){
        if(dataType == null)
            throw new ResourceNotFoundException("Class "+classname+" could not be found in the OWA database");
    }
}
