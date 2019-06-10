package com.stefanini.internship.placemanagement.authorization;

import com.stefanini.internship.placemanagement.data.Identifiable;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;


public class OwaPermissionEvaluator implements PermissionEvaluator {

    public static final String AUTHORIZATION_SERVER_URI = "http://localhost:8086/api/v01/";

    private RestTemplate restTemplate;

    public OwaPermissionEvaluator(RestTemplate restTemplate){
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

        String targetType = identifiableTarget.getClass().getSimpleName();
        return hasPermission(auth, identifiableTarget.getId(), targetType, permission);
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }

        String permissionString = (String)permission;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AUTHORIZATION_SERVER_URI+"authorize")
                .queryParam("principal", auth.getName())
                .queryParam("identifier", targetId)
                .queryParam("permission", permissionString)
                .queryParam("classname", targetType);

        AuthorizationResponse authorization = restTemplate.getForObject(builder.build().toString(), AuthorizationResponse.class);
        return authorization.authorized;
    }
}

