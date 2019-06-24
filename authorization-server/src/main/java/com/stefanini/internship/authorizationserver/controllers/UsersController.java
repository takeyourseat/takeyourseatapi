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

    private AuthorizationUsersService authorizationUsersService;

    private final static Logger logger = Logger.getLogger(PlaceRequestAuthorizationController.class);

    public UsersController(AuthorizationUsersService authorizationUsersService) {
        this.authorizationUsersService = authorizationUsersService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody PostUserRequest user){
        logger.info(String.format("POST controller createUser matched for user = '%s'",user.getUsername()));
        authorizationUsersService.createUser(user);
        logger.debug("Controller createUser responds with HTTP.201");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteUser(@PathVariable String username){
        logger.info(String.format("DELETE controller deleteUser matched for user = '%s'",username));
        authorizationUsersService.deleteUser(username);
        logger.debug("DELETE controller deleteUser return HTTP.204");
        return ResponseEntity.noContent().build();
    }
}
