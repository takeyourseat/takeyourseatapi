package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.Role;
import com.stefanini.internship.authorizationserver.dto.responses.RoleGrantsResponse;
import com.stefanini.internship.authorizationserver.services.RolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION+"roles")
@Slf4j
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles(HttpServletRequest request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        List<Role> roles = rolesService.getAllRoles();
        log.debug("Building success response");
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity createRole(@RequestBody Role role, HttpServletRequest request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        rolesService.createRole(role);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("{role}")
    public ResponseEntity deactivateRole(@PathVariable String role, HttpServletRequest request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        rolesService.deactivateRole(role);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/grants")
    public ResponseEntity<List<RoleGrantsResponse>> getGrantsForAllRoles(HttpServletRequest  request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        List<RoleGrantsResponse> grants = rolesService.getAllRolesGrants();
        log.debug("Building success response");
        return ResponseEntity.ok(grants);
    }

    @GetMapping("{role}/grants")
    public ResponseEntity<RoleGrantsResponse> getRole(@PathVariable String role, HttpServletRequest  request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        RoleGrantsResponse grants = rolesService.getRoleGrants(role);
        log.debug("Building success response");
        return ResponseEntity.ok(grants);
    }

    @PostMapping("{role}/grants/datatypes/{dataType}/{permission}")
    public ResponseEntity grantPermission(@PathVariable String role, @PathVariable String dataType, @PathVariable int permission, HttpServletRequest request){
        log.info(request.getMethod()+" "+request.getRequestURI()+" is matched");
        rolesService.grantPermissionToRole(role, dataType, permission);
        return ResponseEntity.status(201).build();
    }

}
