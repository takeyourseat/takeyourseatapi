package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.Grant;
import com.stefanini.internship.authorizationserver.dao.Role;
import com.stefanini.internship.authorizationserver.services.RolesService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION+"roles/")
@Slf4j
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(HttpServletRequest request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        List<Role> roles = rolesService.getAllUsers();
        log.debug("Building success response");
        return ResponseEntity.ok(roles);
    }

    @GetMapping("{role}/grants")
    public ResponseEntity<List<?>> getRole(@PathVariable String role, HttpServletRequest  request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        List<Grant> grants = rolesService.getRoleGrants(role);
        log.debug("Building success response");
        return ResponseEntity.ok(grants);
    }

}
