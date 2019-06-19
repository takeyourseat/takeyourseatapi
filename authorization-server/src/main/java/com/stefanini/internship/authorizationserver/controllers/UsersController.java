package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dto.PostUserRequest;
import com.stefanini.internship.authorizationserver.services.ObjectIdentitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION+"users")
public class UsersController {

    private ObjectIdentitiesService objectsService;

    public UsersController(ObjectIdentitiesService objectsService) {
        this.objectsService = objectsService;
    }

    @PostMapping
    public ResponseEntity createUserAndSid(@RequestBody PostUserRequest user){
        objectsService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
