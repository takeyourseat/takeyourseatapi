package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.services.AuthorizationService;
import com.stefanini.internship.authorizationserver.dto.AuthorizationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION + "authorizations/datatypes/{classname}/permissions/{permission}")
public class AuthorizationsController {


    private AuthorizationService authorizationService;

    private final static Logger logger = Logger.getLogger(AuthorizationsController.class);

    public AuthorizationsController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public ResponseEntity<AuthorizationResponse> checkClassAuthorization(
            @PathVariable String classname,
            @PathVariable String permission
            ) {
        logger.info(String.format("GET controller checkClassAuthorization is matched with parameters: Classname = '%s' Permission='%s'",classname,permission));
        AuthorizationResponse response = authorizationService.checkAuthorization(classname, permission);
        logger.debug(String.format("GET controller checkClassAuthorization returning HTTP.200 response for parameters: Classname = '%s' Permission='%s'",classname,permission));

        return ResponseEntity.ok(response);
    }
}
