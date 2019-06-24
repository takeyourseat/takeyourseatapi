package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.Grant;
import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.dto.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.utils.AppConstants;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private DataTypeRepository dataTypeRepository;
    private UserRepository userRepository;
    private GrantRepository grantRepository;

    private EntityValidationService validationService;

    final static Logger logger = Logger.getLogger(AuthorizationService.class);

    public AuthorizationService(DataTypeRepository dataTypeRepository, UserRepository userRepository, GrantRepository grantRepository, EntityValidationService validationService) {
        this.dataTypeRepository = dataTypeRepository;
        this.userRepository = userRepository;
        this.grantRepository = grantRepository;
        this.validationService = validationService;
    }

    public AuthorizationResponse checkAuthorization(String classname, String principal, String permissionString) {
        logger.info("Requested permission = '"+permissionString+"' for class = '"+classname+"' for user = '"+principal+"'");
        User user = userRepository.findByUsername(principal);
        validationService.AssertValidResult(user, principal);

        if (user.getRole() == null) {
            logger.info("Permission denied. User has no role. Permission = '"+permissionString+"' for Class = '"+classname+"' for User = '"+principal+"'");
            return new AuthorizationResponse(false, "User has no role in the OWA database");
        }

        DataType dataType = dataTypeRepository.findByName(classname.toUpperCase());
        validationService.AssertValidResult(dataType, classname);

        int permission = AppConstants.getPermission(permissionString.toLowerCase());

        Grant result = grantRepository.findByDataTypeAndRole(dataType, user.getRole());
        boolean isAuthorized;
        if(result == null) {
            isAuthorized = false;
            logger.info("Permission denied. No grant found. Permission = '"+permissionString+"' for Class = '"+classname+"' for User = '"+principal+"'");
        }
        else {
            isAuthorized = result.getPermission() >= permission;
            logger.info("Obtained Grant for Permission = '"+permissionString+"' for Class = '"+classname+"' for User = '"+principal+"'. Access level = '"+result.getPermission()+"' Requested level = '"+permission+"' Authorization = '"+isAuthorized+"'");
        }


        return new AuthorizationResponse(isAuthorized, null);
    }

    public AuthorizationResponse checkAuthorization(String classname, String permission) {

        logger.debug("Permission = "+permission+" for class = '"+classname+"' requested for currently authenticated user");
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return checkAuthorization(classname, authenticatedUserName, permission);
    }

}

