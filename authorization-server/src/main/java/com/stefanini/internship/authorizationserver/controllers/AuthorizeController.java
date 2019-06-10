package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import com.stefanini.internship.authorizationserver.dao.OwaObject;
import com.stefanini.internship.authorizationserver.dao.OwaSid;
import com.stefanini.internship.authorizationserver.dao.OwaRole;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.utils.OwaPermission;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.model.Permission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION + "authorize")
public class AuthorizeController {

    private OwaGrantRepository grantRepository;
    private OwaClassRepository classRepository;
    private OwaSidRepository sidRepository;
    private OwaObjectRepository objectRepository;
    private OwaClassGrantRepository classGrantRepository;
    private OwaRoleRepository roleRepository;

    private final static PermissionFactory permissionFactory = new DefaultPermissionFactory(OwaPermission.class);

    public AuthorizeController(OwaGrantRepository grantRepository, OwaClassRepository classRepository, OwaSidRepository sidRepository, OwaObjectRepository objectRepository, OwaClassGrantRepository classGrantRepository, OwaRoleRepository roleRepository) {
        this.grantRepository = grantRepository;
        this.classRepository = classRepository;
        this.sidRepository = sidRepository;
        this.objectRepository = objectRepository;
        this.classGrantRepository = classGrantRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping(params = {"classname", "principal", "identifier", "permission"})
    public ResponseEntity<AuthorizationResponse> checkAuthorization(
            @RequestParam String classname,
            @RequestParam Long identifier,
            @RequestParam String principal,
            @RequestParam String permission
    ) {
        OwaSid owaSid = sidRepository.findBySid(principal);
        if (owaSid == null) {
            HttpHeaders notFoundHeader = new HttpHeaders();
            notFoundHeader.add("message", "Could not find SID(user) " + principal);
            return ResponseEntity.notFound().headers(notFoundHeader).build();
        }
        if(owaSid.getRole() != null) {
            ResponseEntity<AuthorizationResponse> classGrantCheck = checkClassAuthorization(classname, owaSid.getRole().getName(), permission);
            if (classGrantCheck.getStatusCode() == HttpStatus.OK && classGrantCheck.getBody() != null && classGrantCheck.getBody().isAuthorized())
                return classGrantCheck;
        }

            OwaObject owaObject = objectRepository.findByOwaClassClassnameAndIdentifier(classname, identifier);
        if (owaObject == null) {
            HttpHeaders notFoundHeader = new HttpHeaders();
            notFoundHeader.add("message", "Could not find " + classname + " class with identifier=" + identifier);
            return ResponseEntity.notFound().headers(notFoundHeader).build();
        }



        Permission owaPermission = permissionFactory.buildFromName(permission.toUpperCase());

        boolean result = grantRepository.existsByObjectAndSidAndPermission(owaObject, owaSid, owaPermission.getMask());

        return ResponseEntity.ok(new AuthorizationResponse(result, null));
    }


    @GetMapping(params = {"classname", "role", "permission"})
    public ResponseEntity<AuthorizationResponse> checkClassAuthorization(
            @RequestParam String classname,
            @RequestParam String role,
            @RequestParam String permission
    ) {
        OwaClass owaClass = classRepository.findByClassname(classname);
        if (owaClass == null) {
            HttpHeaders notFoundHeader = new HttpHeaders();
            notFoundHeader.add("message", "Could not find OWA class " + classname);
            return ResponseEntity.notFound().headers(notFoundHeader).build();
        }

        OwaRole owaRole = roleRepository.findByName(role);
        if (owaRole == null) {
            HttpHeaders notFoundHeader = new HttpHeaders();
            notFoundHeader.add("message", "Could not find SID(user) " + role);
            return ResponseEntity.notFound().headers(notFoundHeader).build();
        }

        Permission owaPermission = permissionFactory.buildFromName(permission.toUpperCase());

        boolean result = classGrantRepository.existsByOwaClassAndRoleAndPermission(owaClass, owaRole, owaPermission.getMask());
        return ResponseEntity.ok(new AuthorizationResponse(result, null));
    }
}
