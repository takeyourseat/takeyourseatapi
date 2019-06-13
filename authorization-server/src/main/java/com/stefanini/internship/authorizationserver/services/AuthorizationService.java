package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.OwaClass;
import com.stefanini.internship.authorizationserver.dao.OwaObject;
import com.stefanini.internship.authorizationserver.dao.OwaSid;
import com.stefanini.internship.authorizationserver.dao.repositories.*;
import com.stefanini.internship.authorizationserver.utils.AuthorizationResponse;
import com.stefanini.internship.authorizationserver.utils.OwaPermission;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthorizationService {

    private OwaGrantRepository grantRepository;
    private OwaClassRepository classRepository;
    private OwaSidRepository sidRepository;
    private OwaObjectRepository objectRepository;
    private OwaRoleRepository roleRepository;
    private OwaClassGrantRepository classGrantRepository;

    private EntityValidationService validationService;


    private final static PermissionFactory permissionFactory = new DefaultPermissionFactory(OwaPermission.class);

    public AuthorizationService(OwaGrantRepository grantRepository, OwaClassRepository classRepository, OwaSidRepository sidRepository, OwaObjectRepository objectRepository, OwaRoleRepository roleRepository, OwaClassGrantRepository classGrantRepository, EntityValidationService validationService) {
        this.grantRepository = grantRepository;
        this.classRepository = classRepository;
        this.sidRepository = sidRepository;
        this.objectRepository = objectRepository;
        this.roleRepository = roleRepository;
        this.classGrantRepository = classGrantRepository;
        this.validationService = validationService;

    }

    public AuthorizationResponse checkClassAuthorization(String classname, String principal, String permission) {

        OwaSid owaSid = sidRepository.findBySid(principal);
        validationService.AssertValidResult(owaSid, principal);

        if (owaSid.getRole() == null)
            return new AuthorizationResponse(false, "User has no role in the OWA database");

        OwaClass owaClass = classRepository.findByClassname(classname);
        validationService.AssertValidResult(owaClass, classname);

        Permission owaPermission = permissionFactory.buildFromName(permission.toUpperCase());

        boolean result = classGrantRepository.existsByOwaClassAndRoleAndPermission(owaClass, owaSid.getRole(), owaPermission.getMask());
        return new AuthorizationResponse(result, null);
    }

    public AuthorizationResponse checkClassAuthorization(String classname, String permission) {
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return checkClassAuthorization(classname, authenticatedUserName, permission);
    }

    public AuthorizationResponse checkObjectAuthorization(String classname, Long identifier, String principal, String permission) {
        OwaSid owaSid = sidRepository.findBySid(principal);
        validationService.AssertValidResult(owaSid, principal);

        /* Check if user has authorization for the entire OwaClass */
        if (owaSid.getRole() != null) {
            AuthorizationResponse classGrantCheck = checkClassAuthorization(classname, owaSid.getSid(), permission);
            if (classGrantCheck.isAuthorized())
                return classGrantCheck;
        }

        OwaObject owaObject = objectRepository.findByOwaClassClassnameAndIdentifier(classname, identifier);
        validationService.AssertValidResult(owaObject, classname, identifier);

        Permission owaPermission = permissionFactory.buildFromName(permission.toUpperCase());

        boolean result = grantRepository.existsByObjectAndSidAndPermission(owaObject, owaSid, owaPermission.getMask());

        return new AuthorizationResponse(result, null);
    }

    public AuthorizationResponse checkObjectAuthorization(String classname, Long identifier, String permission) {
        String authenticatedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return checkObjectAuthorization(classname, identifier, authenticatedUserName, permission);
    }
}

