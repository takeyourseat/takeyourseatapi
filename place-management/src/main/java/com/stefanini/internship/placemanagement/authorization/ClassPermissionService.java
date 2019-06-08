package com.stefanini.internship.placemanagement.authorization;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.domain.PermissionFactory;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.stefanini.internship.placemanagement.authorization.OwaPermissionEvaluator.AUTHORIZATION_SERVER_URI;

@Component("ClassPermissionService")
public class ClassPermissionService {


    private RestTemplate restTemplate;

    public ClassPermissionService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean hasClassPermission(Authentication auth, String classname, String permission){
        if ((auth == null) || (classname == null) || (permission == null)) {
            return false;
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AUTHORIZATION_SERVER_URI+"authorize")
                .queryParam("principal", auth.getName())
                .queryParam("permission", permission)
                .queryParam("classname", classname);

        AuthorizationResponse authorization = restTemplate.getForObject(builder.build().toString(), AuthorizationResponse.class);

        return authorization.authorized;
    }
}
