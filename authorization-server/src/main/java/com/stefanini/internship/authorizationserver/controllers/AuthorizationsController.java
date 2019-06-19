package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.services.AuthorizationService;
import com.stefanini.internship.authorizationserver.dto.AuthorizationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION + "authorizations/datatypes/{classname}/permissions/{permission}")
public class AuthorizationsController {


    private AuthorizationService authorizationService;

    public AuthorizationsController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public ResponseEntity<AuthorizationResponse> checkClassAuthorization(
            @PathVariable String classname,
            @PathVariable String permission
            ) {
        AuthorizationResponse response = authorizationService.checkAuthorization(classname, permission);
        return ResponseEntity.ok(response);
    }
}
