package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import com.stefanini.internship.authorizationserver.dao.OwaObject;
import com.stefanini.internship.authorizationserver.dao.OwaSid;
import com.stefanini.internship.authorizationserver.dao.OwaRole;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.utils.EntityValidation;
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
        EntityValidation.AssertValidResult(owaSid,principal);

        if(owaSid.getRole() != null) {
            ResponseEntity<AuthorizationResponse> classGrantCheck = checkClassAuthorization(classname, owaSid.getSid(), permission);
            if (classGrantCheck.getStatusCode() == HttpStatus.OK && classGrantCheck.getBody() != null && classGrantCheck.getBody().isAuthorized())
                return classGrantCheck;
        }

        OwaObject owaObject = objectRepository.findByOwaClassClassnameAndIdentifier(classname, identifier);
        EntityValidation.AssertValidResult(owaObject, classname, identifier);

        Permission owaPermission = permissionFactory.buildFromName(permission.toUpperCase());

        boolean result = grantRepository.existsByObjectAndSidAndPermission(owaObject, owaSid, owaPermission.getMask());

        return ResponseEntity.ok(new AuthorizationResponse(result, null));
    }


    @GetMapping(params = {"classname", "principal", "permission"})
    public ResponseEntity<AuthorizationResponse> checkClassAuthorization(
            @RequestParam String classname,
            @RequestParam String principal,
            @RequestParam String permission
    ) {
        OwaClass owaClass = classRepository.findByClassname(classname);
        EntityValidation.AssertValidResult(owaClass, classname);

        OwaSid owaSid = sidRepository.findBySid(principal);
        EntityValidation.AssertValidResult(owaSid, principal);

        Permission owaPermission = permissionFactory.buildFromName(permission.toUpperCase());

        if(owaSid.getRole()==null)
            return ResponseEntity.ok(new AuthorizationResponse(false, "User has no role in the OWA database"));


        boolean result = classGrantRepository.existsByOwaClassAndRoleAndPermission(owaClass, owaSid.getRole(), owaPermission.getMask());
        return ResponseEntity.ok(new AuthorizationResponse(result, null));
    }
}
