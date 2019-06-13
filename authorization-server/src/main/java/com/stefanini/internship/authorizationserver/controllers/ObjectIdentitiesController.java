package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.*;
import com.stefanini.internship.authorizationserver.dao.classes.PlaceRequest;
import com.stefanini.internship.authorizationserver.dao.classes.User;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.exceptions.ResourceNotFoundException;
import com.stefanini.internship.authorizationserver.services.ObjectIdentitiesService;
import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.services.EntityValidationService;
import com.stefanini.internship.authorizationserver.utils.OwaPermission;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION+"objects")
public class ObjectIdentitiesController {

    private ObjectIdentitiesService objectsService;

    public ObjectIdentitiesController(ObjectIdentitiesService objectsService) {
        this.objectsService = objectsService;
    }

    @PostMapping("/users")
    public ResponseEntity createUserAndSid(@RequestBody User user){
        objectsService.createUserAndSid(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/place-requests")
    public ResponseEntity createPlaceRequest(@RequestBody PlaceRequest placeRequest){
        objectsService.createPlaceRequest(placeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
