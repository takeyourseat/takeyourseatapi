package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.Grant;
import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.dto.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.utils.AppConstants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private DataTypeRepository dataTypeRepository;
    private UserRepository userRepository;
    private GrantRepository grantRepository;

    private EntityValidationService validationService;

    public AuthorizationService(DataTypeRepository dataTypeRepository, UserRepository userRepository, GrantRepository grantRepository, EntityValidationService validationService) {
        this.dataTypeRepository = dataTypeRepository;
        this.userRepository = userRepository;
        this.grantRepository = grantRepository;
        this.validationService = validationService;
    }

    public AuthorizationResponse checkAuthorization(String classname, String principal, String permissionString) {

        User user = userRepository.findByUsername(principal);
        validationService.AssertValidResult(user, principal);

        if (user.getRole() == null)
            return new AuthorizationResponse(false, "PostUserRequest has no role in the OWA database");

        DataType dataType = dataTypeRepository.findByName(classname.toUpperCase());
        validationService.AssertValidResult(dataType, classname);

        int permission = AppConstants.getPermission(permissionString.toLowerCase());

        Grant result = grantRepository.findByDataTypeAndRole(dataType, user.getRole());
        boolean isAuthorized;
        if(result == null)
            isAuthorized=false;
        else
            isAuthorized = result.getPermission() >= permission;
        return new AuthorizationResponse(isAuthorized, null);
    }

    public AuthorizationResponse checkAuthorization(String classname, String permission) {
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return checkAuthorization(classname, authenticatedUserName, permission);
    }

}

