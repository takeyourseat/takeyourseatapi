package com.stefanini.internship.usermanagement.authorization;

import com.stefanini.internship.usermanagement.authentication.AuthenticationUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.persistence.PostPersist;

import static com.stefanini.internship.usermanagement.authorization.AuthorizationConstants.AUTHORIZATION_API;

public class AuthorizationRegistrationListener {

    private RestTemplate restTemplate = new RestTemplate();

    public AuthorizationRegistrationListener(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostPersist
    public void registerObjectToAuthorizationServer(final Object persistedEntity) {
        HttpEntity<Object> request = new HttpEntity<>(persistedEntity, AuthenticationUtils.getAuthorizationHeader());
        restTemplate.exchange(AUTHORIZATION_API+"objects/users", HttpMethod.POST, request, Object.class);
    }
}
