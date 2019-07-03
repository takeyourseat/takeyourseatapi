package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.controllers.PlaceRequestAuthorizationController;
import com.stefanini.internship.authorizationserver.dao.User;
import com.stefanini.internship.authorizationserver.dao.repositories.UserRepository;
import com.stefanini.internship.authorizationserver.dto.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.dto.PlaceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Slf4j
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

        log.info(String.format("Authorization requested for PlaceRequest with id='%d' for Permission='%s'",placeRequest.getId(), permissionString));
        log.debug(String.format("Check if class-wide permission='%s' for 'PlaceRequest' is granted",permissionString));
        AuthorizationResponse roleRequest = authorizationService.checkAuthorization("PlaceRequest", "write");
        if(roleRequest.isAuthorized()) {
            log.info(String.format("Authorization granted for PlaceRequest with id='%d' for Permission='%s' due to class-wide permission",placeRequest.getId(), permissionString));
            return roleRequest;
        }
        switch (permissionString.toLowerCase()){
            case "read":
                return canUserReadPlaceRequest(placeRequest);
            case "approve":
                return canUserApprovePlaceRequest(placeRequest);
            default:
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Unexpected permission "+permissionString);
        }

    }
    private AuthorizationResponse canUserReadPlaceRequest(PlaceRequest placeRequest){
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        log.debug(String.format("Checking read permission for User='%s' for PlaceRequest with id = '%d'",authenticatedUserName,placeRequest.getId()));

        User requestedManager = userService.getUserManager(placeRequest.getUsername());
        boolean isViewedByCreator = authenticatedUserName.equals(placeRequest.getUsername());
        boolean isViewedByManager = authenticatedUserName.equals(requestedManager.getUsername());

        log.debug(String.format("Responding with authorization based on isViedByCreator='%s' or isViewedByManager='%s'", isViewedByCreator, isViewedByManager));
        return new AuthorizationResponse(isViewedByCreator || isViewedByManager,null);
    }

    private AuthorizationResponse canUserApprovePlaceRequest(PlaceRequest placeRequest){
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        User requestManager = userService.getUserManager(placeRequest.getUsername());

        boolean isAuthorized = requestManager.getUsername().equals(authenticatedUserName);
        log.debug(String.format("Responding with authorization based on isViewedByManager='%s'", isAuthorized));

        return new AuthorizationResponse(isAuthorized,null);
    }

}
