package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dto.PostUserRequest;
import com.stefanini.internship.authorizationserver.services.AuthorizationUsersService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION+"users")
public class UsersController {

    private AuthorizationUsersService objectsService;

    private final static Logger logger = Logger.getLogger(PlaceRequestAuthorizationController.class);

    public UsersController(AuthorizationUsersService objectsService) {
        this.objectsService = objectsService;
    }

    @PostMapping
    public ResponseEntity createUserAndSid(@RequestBody PostUserRequest user){
        logger.info(String.format("POST controller createUserAndSid matched for user = '%s'",user.getUsername()));
        objectsService.createUser(user);
        logger.debug("Controller createUserAndSid responds with HTTP.201");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
