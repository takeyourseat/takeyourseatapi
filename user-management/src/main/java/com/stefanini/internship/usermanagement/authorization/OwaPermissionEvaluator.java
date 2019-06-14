package com.stefanini.internship.usermanagement.authorization;

import com.stefanini.internship.usermanagement.authentication.AuthenticationUtils;
import com.stefanini.internship.usermanagement.dao.Identifiable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

import static com.stefanini.internship.usermanagement.authorization.AuthorizationConstants.AUTHORIZATION_API;


public class OwaPermissionEvaluator implements PermissionEvaluator {

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

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AUTHORIZATION_API+"authorize")
                .queryParam("principal", auth.getName())
                .queryParam("identifier", targetId)
                .queryParam("permission", permissionString)
                .queryParam("classname", targetType);

        HttpEntity<String> request = new HttpEntity<>(AuthenticationUtils.getAuthorizationHeader());
        ResponseEntity<AuthorizationResponse> authorization = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, AuthorizationResponse.class);
        return authorization.getBody().authorized;
    }

}

