package com.stefanini.internship.authorizationserver.utils;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import com.stefanini.internship.authorizationserver.dao.OwaObject;
import com.stefanini.internship.authorizationserver.dao.OwaSid;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;

public class EntityValidation {
    private EntityValidation() {
    }

    public static void AssertValidResult(OwaSid sid, String expected){
        if(sid == null)
           throw new ResourceNotFoundException("User "+expected+" could not be found");
    }

    public static void AssertValidResult(OwaObject object, String classname, Long identifier){
        if(object == null)
            throw new ResourceNotFoundException("Object of class "+classname+" with identifier "+ identifier +" could not be found");
    }

    public static void AssertValidResult(OwaClass owaClass, String classname){
        if(owaClass == null)
            throw new ResourceNotFoundException("Class "+classname+" could not be found in the OWA database");
    }

}
