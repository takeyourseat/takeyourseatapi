package com.stefanini.internship.placemanagement.authorization;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.stefanini.internship.placemanagement.authorization.AuthorizationUtils.AUTHORIZATION_API;

@Component("ClassGrantService")
public class ClassGrantService {


    private RestTemplate restTemplate;

    public ClassGrantService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean hasClassGrant(Authentication auth, String classname, String permission){
        if ((auth == null) || (classname == null) || (permission == null)) {
            return false;
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AUTHORIZATION_API+"authorize")
                .queryParam("principal", auth.getName())
                .queryParam("permission", permission)
                .queryParam("classname", classname);

        AuthorizationResponse authorization = restTemplate.getForObject(builder.build().toString(), AuthorizationResponse.class);

        return authorization.authorized;
    }
}
