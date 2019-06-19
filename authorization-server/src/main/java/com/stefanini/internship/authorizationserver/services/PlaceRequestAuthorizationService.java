package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.dao.repositories.UserRepository;
import com.stefanini.internship.authorizationserver.dto.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.dto.PlaceRequest;
import com.stefanini.internship.authorizationserver.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class PlaceRequestAuthorizationService {

    private AuthorizationService authorizationService;
    private UserRepository userRepository;
    private UserService userService;

    public PlaceRequestAuthorizationService(AuthorizationService authorizationService, UserRepository userRepository, UserService userService) {
        this.authorizationService = authorizationService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public AuthorizationResponse authorizePlaceRequest(PlaceRequest placeRequest, String permissionString){

        /* check if user has class-wide authorization*/
        AuthorizationResponse roleRequest = authorizationService.checkAuthorization("PlaceRequest", permissionString);
        if(roleRequest.isAuthorized())
            return roleRequest;
        int permission = AppConstants.getPermission(permissionString);
        switch (permission){
            case 1:
                return canUserReadPlaceRequest(placeRequest);
            case 2:
                return canUserWritePlaceRequest(placeRequest);
            default:
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Unexpected permission "+permissionString);
        }

    }
    private AuthorizationResponse canUserReadPlaceRequest(PlaceRequest placeRequest){
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User authenticatedUser = userRepository.findByUsername(authenticatedUserName);
        User requestedManager = userService.getUserManager(placeRequest.getId());
        boolean isViewedByCreator = placeRequest.getUserId().equals(authenticatedUser.getId());
        boolean isViewedByManager = requestedManager.getUsername().equals(authenticatedUserName);

        return new AuthorizationResponse(isViewedByCreator || isViewedByManager,null);
    }

    private AuthorizationResponse canUserWritePlaceRequest(PlaceRequest placeRequest){
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        User requestManager = userService.getUserManager(placeRequest.getUserId());

        boolean isAuthorized = requestManager.getUsername().equals(authenticatedUserName);
        return new AuthorizationResponse(isAuthorized,null);
    }

}
