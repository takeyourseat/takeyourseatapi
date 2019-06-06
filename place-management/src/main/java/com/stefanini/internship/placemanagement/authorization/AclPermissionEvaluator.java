package com.stefanini.internship.placemanagement.authorization;

import com.stefanini.internship.placemanagement.data.Identifiable;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.Serializable;


public class AclPermissionEvaluator implements PermissionEvaluator {

    public static final String AUTHORIZATION_SERVER_URI = "http://localhost:8086/api/get-authorization";

    private RestTemplate restTemplate;

    public AclPermissionEvaluator(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }

        Identifiable identifiableTarget;
        if (targetDomainObject instanceof HttpEntity) {
            HttpEntity entity = (HttpEntity)targetDomainObject;
            identifiableTarget = (Identifiable) entity.getBody();
        }
        else {
            identifiableTarget = (Identifiable)targetDomainObject;
        }


        String targetType = identifiableTarget.getClass().getSimpleName().toUpperCase();

        return hasPermission(auth, identifiableTarget.getId(), targetType, permission);
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }

        String permissionString = ((String)permissionObject).toUpperCase();
        PermissionFactory permissionFactory = new DefaultPermissionFactory(BasePermission.class);
        Permission permission = permissionFactory.buildFromName(permissionString);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AUTHORIZATION_SERVER_URI)
                .queryParam("principal", auth.getName())
                .queryParam("identifier", targetId)
                .queryParam("mask", permission.getMask())
                .queryParam("classname", targetType);

        AuthorizationResponse authorization = restTemplate.getForObject(builder.build().toString(), AuthorizationResponse.class);

        if(authorization.message!=null)
            System.out.println(authorization.message);
        return authorization.authorized;

    }
}

