package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dto.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.dto.PlaceRequest;
import com.stefanini.internship.authorizationserver.services.PlaceRequestAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@Slf4j
@RequestMapping(API_ROOT_VERSION + "authorizations/placerequests/permissions/{permission}")
public class PlaceRequestAuthorizationController {

    private PlaceRequestAuthorizationService placeRequestAuthorizationService;

    public PlaceRequestAuthorizationController(PlaceRequestAuthorizationService placeRequestAuthorizationService){
        this.placeRequestAuthorizationService = placeRequestAuthorizationService;
    }

    @PostMapping
    public ResponseEntity<AuthorizationResponse> authorizeSpecificPlaceRequest(@RequestBody PlaceRequest placeRequest, @PathVariable String  permission){
        log.info(String.format("Currently logged in user requests permission '%s' for place request with id = '%d' ", permission, placeRequest.getId()));
        AuthorizationResponse response = placeRequestAuthorizationService.authorizePlaceRequest(placeRequest, permission);
        log.debug("Returning HTTP.200 response");
        return ResponseEntity.ok(response);
    }
}
