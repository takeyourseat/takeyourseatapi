package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.OwaObject;
import com.stefanini.internship.authorizationserver.dao.OwaSid;
import com.stefanini.internship.authorizationserver.services.AuthorizationService;
import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.services.EntityValidationService;
import com.stefanini.internship.authorizationserver.utils.OwaPermission;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.model.Permission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION + "authorize")
public class AuthorizeController {


    private AuthorizationService authorizationService;

    private final static PermissionFactory permissionFactory = new DefaultPermissionFactory(OwaPermission.class);

    public AuthorizeController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping(params = {"classname", "principal", "identifier", "permission"})
    public ResponseEntity<AuthorizationResponse> checkAuthorization(
            @RequestParam String classname,
            @RequestParam Long identifier,
            @RequestParam String principal,
            @RequestParam String permission
    ) {
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname,identifier,principal,permission);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"classname", "identifier", "permission"})
    public ResponseEntity<AuthorizationResponse> checkAuthorization(
            @RequestParam String classname,
            @RequestParam Long identifier,
            @RequestParam String permission
    ) {
        AuthorizationResponse response = authorizationService.checkObjectAuthorization(classname, identifier, permission);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"classname", "principal", "permission"})
    public ResponseEntity<AuthorizationResponse> checkClassAuthorization(
            @RequestParam String classname,
            @RequestParam String principal,
            @RequestParam String permission
    ) {
        AuthorizationResponse response = authorizationService.checkClassAuthorization(classname, principal, permission);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"classname", "permission"})
    public ResponseEntity<AuthorizationResponse> checkClassAuthorization(
            @RequestParam String classname,
            @RequestParam String permission
            ) {
        AuthorizationResponse response = authorizationService.checkClassAuthorization(classname, permission);
        return ResponseEntity.ok(response);
    }
}
